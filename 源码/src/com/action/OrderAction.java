package com.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.entity.Books;
import com.entity.Customers;
import com.entity.Imports;
import com.entity.Orders;
import com.entity.Shops;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.print.BookItems;
import com.print.BuyValue;
import com.print.OrderCustomer;
import com.print.OrderShop;
import com.print.ShopCart;
import com.print.TableShow;
import com.service.BookService;
import com.service.CustomerService;
import com.service.ImportService;
import com.service.OrderService;
import com.service.ShopService;

@Controller
@Scope("prototype")
public class OrderAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired private OrderService orderService;
	@Autowired private CustomerService customerService;
	@Autowired private BookService bookService;
	@Autowired private ShopService shopService;
	@Autowired private ImportService importService;
	private Orders bookorder = new Orders();
	BuyValue buyvalue = new BuyValue();
	private String id = null;
	private Long num = (long) 0;
	private Integer pay = 0;
	private Date postdate = new Date();
	private Date nowdate = new Date();
	private Map<String, Object> session;
	
	public Orders getBookorder() {
		return bookorder;
	}

	public void setBookorder(Orders bookorder) {
		this.bookorder = bookorder;
	}

	public BuyValue getBuyvalue() {
		return buyvalue;
	}

	public void setBuyvalue(BuyValue buyvalue) {
		this.buyvalue = buyvalue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public Date getNowdate() {
		return nowdate;
	}

	public void setNowdate(Date nowdate) {
		this.nowdate = nowdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//添加图书至购物车
	public String bookBuy() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		//获取当前登录用户名
		String username = (String) session.get("Cusername");
		//当前未有顾客登录，则跳至登录提示界面
		if(username == null) {
			return "nologin";
		}else {
			//bookitem为某一种图书的所有出售商家集合
			//id返回的是店员用户名
			//从之前会话中记录的bookitem中找出顾客所指定商家的相关记录
			@SuppressWarnings("unchecked")
			List<BookItems> bookitems = (List<BookItems>) session.get("bookitems");
			Iterator<BookItems> it = bookitems.iterator();
			BookItems bookitem = null;
			while(it.hasNext()) {
				bookitem = it.next();
				//用店员用户名找到该条信息后退出查找
				if(bookitem.getSusername().equals(id)) {
					break;
				}
			}
			//如果该商家中该图书的库存量小于或等于0，直接返回ERROR对应界面
			if(bookitem.getIgoodsnumber() <= 0) {
				return ERROR;
			}else {
				//否则，加入购物车
				//检查购物车中已经存在
				Long onum = orderService.isExtraOrder(username, bookitem.getSusername(),
						bookitem.getBnumber());
				//存在，则在原先基础上数量加1
				if(onum >= 0) {
					orderService.changeGoodsNum(onum, 1);
				}else {
					//不存在，新插入一个未付款订单
					orderService.insertOrder(username, bookitem.getSusername(),
							bookitem.getBnumber(), "Internet", 1, bookitem.getBnowprice(),
							null, false, 0);
				}
				//修改相应书店库存量
				importService.changeGoodsNumber(bookitem.getSusername(),
						bookitem.getBnumber(), -1);
				return SUCCESS;
			}
		}
	}
	//顾客查看购物车
	public String checkCart() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String username = (String) session.get("Cusername");
		if(username == null) {
			return ERROR;
		}else {
			List<Orders> orders = orderService.showCart(username);
			List<ShopCart> carts = new ArrayList<>();
			Books book = new Books();
			Shops shop = new Shops();
			Imports imports = new Imports();
			Iterator<Orders> it = orders.iterator();
			while(it.hasNext()) {
				Orders order = it.next();
				ShopCart cart = new ShopCart();
				cart.setOnumber(order.getOnumber());
				book = bookService.BookByNumber(order.getBooks().getBnumber());
				cart.setBnumber(order.getBooks().getBnumber());
				cart.setBname(book.getBname());
				shop = shopService.ShopByUsername(order.getShops().getSusername());
				cart.setSshopname(shop.getSshopname());
				cart.setSphone(shop.getSphone());
				cart.setOquantity(order.getOquantity());
				imports = importService.oneImports(shop.getSusername(), book.getBnumber());
				cart.setBnowprice(imports.getIdiscount() * book.getBprice());
				carts.add(cart);
			}
			Collections.sort(carts);
			if(session.get("cart") != null) {
				session.replace("cart", carts);
			}else {
				session.put("cart", carts);
			}
			return SUCCESS;
		}
	}
	
	public String addQuantity() {
		//cart对应数目的增加
		//Imports的修改
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		Orders order = orderService.OrderByNumber(num);
		Imports imports = importService.oneImports(order.getShops().getSusername(),
				order.getBooks().getBnumber());
		@SuppressWarnings("unchecked")
		List<ShopCart> carts = (List<ShopCart>) session.get("cart");
		ShopCart cart = new ShopCart();
		Iterator<ShopCart> it = carts.iterator();
		while(it.hasNext()) {
			cart = it.next();
			if(cart.getOnumber() == num) {
				break;
			}
		}
		if(imports.getInumgoods() == 0) {
			return ERROR;
		}else {
			//存量-1
			importService.changeGoodsNumber(imports.getKey().getIusername(),
					imports.getKey().getInumber(), -1);
			//购物车+1
			carts.remove(cart);
			cart.setOquantity(cart.getOquantity() + 1);
			carts.add(cart);
			Collections.sort(carts);
			orderService.changeGoodsNum(order.getOnumber(), 1);
			return SUCCESS;
		}
		
	}
	
	public String subQuantity() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		Orders order = orderService.OrderByNumber(num);
		@SuppressWarnings("unchecked")
		List<ShopCart> carts = (List<ShopCart>) session.get("cart");
		ShopCart cart = new ShopCart();
		Iterator<ShopCart> it = carts.iterator();
		while(it.hasNext()) {
			cart = it.next();
			if(cart.getOnumber() == num) {
				break;
			}
		}
		importService.changeGoodsNumber(order.getShops().getSusername(),
				order.getBooks().getBnumber(), 1);
		if(cart.getOquantity() == 1) {
			orderService.dropOrder(order.getOnumber());
			carts.remove(cart);
		}else {
			orderService.changeGoodsNum(order.getOnumber(), -1);
			carts.remove(cart);
			cart.setOquantity(cart.getOquantity() - 1);
			carts.add(cart);
		}
		Collections.sort(carts);
		return SUCCESS;
	}
	
	//点击购买按钮
	public String purchaseOrder() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		//会话中的Onumber用于记录当前订单号
		//记录当前订单号的值为所需购买的订单
		if(session.get("Onumber") != null) {
			session.replace("Onumber", num); //存在记录变量，则修改其值
		}else {
			session.put("Onumber", num); //不存在则创建记录变量记录该值
		}
		//取出该订单
		bookorder = orderService.OrderByNumber(num);
		Books book = bookService.BookByNumber(bookorder.getBooks().getBnumber());
		//取出相应店家该种图书的基本情况(使用店家折扣)
		Imports imports = importService.oneImports(bookorder.getShops().getSusername(),
				bookorder.getBooks().getBnumber());
		//修改订单总价和获得积分栏
		bookorder.setOsumprice(bookorder.getOquantity() * book.getBprice() * imports.getIdiscount());
		bookorder.setOgetvalue(bookorder.getOquantity() * buyvalue.getPurchaseValue());
		orderService.saveOrder(bookorder); //保存订单
		return SUCCESS;
	}
	
	//订单信息修正及提交订单
	public String buyCertain() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		//利用会话中的Onumber值取出交易订单
		bookorder = orderService.OrderByNumber((Long)(session.get("Onumber")));
		//利用会话中的用户名获取用户信息
		String username = (String) session.get("Cusername");
		Customers customer = customerService.CustomerByUsername(username);
		//确认地址
		//num为1是采用用户输入地址，为0时采用用户默认地址
		if(num == (long)1) {
			bookorder.setOlocal(id);
		}else {
			bookorder.setOlocal(customer.getClocal());
		}
		//是否积分打折
		if(pay == 1) {
			//采用积分打折机制
			Integer needvalue = (buyvalue.getDiscountValue() *
					bookorder.getOquantity()); //需要的积分数
			//积分不足，则返回错误界面
			if(customer.getCvalue() < needvalue) {
				return ERROR;
			}else {
				//否则修改订单记录的积分打折变量为true并在顾客的积分处将相应积分减去
				//修改最终交付额
				bookorder.setOdiscount(true);
				customer.setCvalue(customer.getCvalue() - needvalue);
				bookorder.setOsumprice(bookorder.getOsumprice() *
						buyvalue.getDiscount());
			}
		}
		//顾客积分添加购书后的奖励积分
		customer.setCvalue(customer.getCvalue() + bookorder.getOgetvalue());
		customerService.saveCustomer(customer); //保存顾客信息
		bookorder.setOpay(true); //标记订单为以支付
		//记录交易时间
		Timestamp otime = new Timestamp(System.currentTimeMillis());
		bookorder.setOtime(otime);
		orderService.saveOrder(bookorder); //保存订单
		session.remove("Onumber"); //将该订单号从当前会话中取出(完成购买)
		return SUCCESS;
	}
	
	public String checkOrder() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String username = (String) session.get("Cusername");
		if(username == null) {
			return ERROR;
		}else {
			List<Orders> orders = orderService.showOrder(username);
			List<OrderCustomer> ordercustomers = new ArrayList<>();
			Books book = new Books();
			Shops shop = new Shops();
			Iterator<Orders> it = orders.iterator();
			while(it.hasNext()) {
				Orders order = it.next();
				OrderCustomer ordercustomer = new OrderCustomer();
				ordercustomer.setOnumber(order.getOnumber());
				book = bookService.BookByNumber(order.getBooks().getBnumber());
				ordercustomer.setBnumber(book.getBnumber());
				ordercustomer.setBname(book.getBname());
				shop = shopService.ShopByUsername(order.getShops().getSusername());
				ordercustomer.setSshopname(shop.getSshopname());
				ordercustomer.setSphone(shop.getSphone());
				ordercustomer.setOquantity(order.getOquantity());
				ordercustomer.setOsumprice(order.getOsumprice());
				if(order.getOaccept() == true) {
					ordercustomer.setOaccept("已发货");
				}else {
					ordercustomer.setOaccept("未发货");
				}
				if(order.getOcancel() == true) {
					ordercustomer.setOcancel("已退款");
				}else {
					ordercustomer.setOcancel("未退款");
				}
				ordercustomers.add(ordercustomer);
			}
			Collections.sort(ordercustomers);
			if(session.get("ordercustomer") != null) {
				session.replace("ordercustomer", ordercustomers);
			}else {
				session.put("ordercustomer", ordercustomers);
			}
			return SUCCESS;
		}
	}
	
	//是否退单
	public String submitReturn() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		if(session.get("Onumber") != null) {
			session.replace("Onumber", num);
		}else {
			session.put("Onumber", num);
		}
		return "success";
	}
	
	public String returnReason() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		bookorder = orderService.OrderByNumber((Long)(session.get("Onumber")));
		bookorder.setOreason(id);
		bookorder.setOreturn(true);
		orderService.saveOrder(bookorder);
		session.remove("Onumber");
		return SUCCESS;
	}
	
	public String dealOrder() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String username = (String) session.get("Susername");
		if(username == null) {
			return ERROR;
		}else {
			List<Orders> orders = orderService.checkOrder(username);
			List<OrderShop> ordershops = new ArrayList<>();
			Books book = new Books();
			Customers customer = new Customers();
			Iterator<Orders> it = orders.iterator();
			while(it.hasNext()) {
				Orders order = it.next();
				if(order.getOcancel() == true) {
					continue;
				}
				OrderShop ordershop = new OrderShop();
				ordershop.setOnumber(order.getOnumber());
				book = bookService.BookByNumber(order.getBooks().getBnumber());
				ordershop.setBnumber(book.getBnumber());
				ordershop.setBname(book.getBname());
				customer = customerService.CustomerByUsername(order.getCustomers()
						.getCusername());
				ordershop.setCusername(customer.getCusername());
				ordershop.setCphone(customer.getCphone());
				ordershop.setOquantity(order.getOquantity());
				ordershop.setOsumprice(order.getOsumprice());
				ordershop.setOlocal(order.getOlocal());
				if(order.getOaccept() == false) {
					ordershop.setOsend(true);
					ordershop.setOback(false);
				}else {
					if(order.getOreturn() == true && order.getOcancel() == false) {
						ordershop.setOsend(false);
						ordershop.setOback(true);
					}else {
						ordershop.setOsend(false);
						ordershop.setOback(false);
					}
				}
				ordershops.add(ordershop);
			}
			Collections.sort(ordershops);
			if(session.get("ordershop") != null) {
				session.replace("ordershop", ordershops);
			}else {
				session.put("ordershop", ordershops);
			}
			return SUCCESS;
		}
	}
	
	//确认订单
	public String acceptOrder() {
		//num储存着相应订单值
		//取出相关订单
		bookorder = orderService.OrderByNumber(num);
		bookorder.setOaccept(true); //修改订单状态为接受
		orderService.saveOrder(bookorder); //保存订单
		return SUCCESS;	
	}
	
	//确认退单
	public String returnOrder() {
		//num储存着相应订单值
		//取出相关订单
		bookorder = orderService.OrderByNumber(num);
		bookorder.setOcancel(true); //修改退单状态为接受
		//取出该订单相应顾客的信息
		Customers customer = customerService.CustomerByUsername(bookorder.getCustomers().getCusername());
		Integer temp = 0;
		//当顾客使用积分打折时，记录temp=1
		if(bookorder.getOdiscount() == true) {
			temp = 1;
		}
		//回滚顾客积分
		customer.setCvalue(customer.getCvalue() - bookorder.getOgetvalue()
				+ buyvalue.getDiscountValue() * bookorder.getOquantity() * temp);
		customerService.saveCustomer(customer); //保存顾客信息
		//回滚店家图书库存量
		importService.changeGoodsNumber(bookorder.getShops().getSusername(),
				bookorder.getBooks().getBnumber(), bookorder.getOquantity());
		orderService.saveOrder(bookorder); //保存订单
		return SUCCESS;
	}
	
	//拒绝退单
	public String refuseOrder() {
		//num储存着相应订单值
		//取出相关订单
		bookorder = orderService.OrderByNumber(num);
		bookorder.setOreturn(false); //修改申请订单状态为否
		orderService.saveOrder(bookorder); //保存订单
		return SUCCESS;
	}
	
	//报表生成与查看
	public String showTable() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		//获取当前店员用户名
		String username = (String) session.get("Susername");
		//检测实现上限是否小于等于时间下限
		if(postdate.getTime() <= nowdate.getTime()) {
			//相等，则进行报表生成
			List<TableShow> tableshows = orderService.submitTable(username, postdate, nowdate);
			//将报表信息存入会话中
			if(session.get("table") != null) {
				session.replace("table", tableshows);
			}else {
				session.put("table", tableshows);
			}
			return SUCCESS;
		}else{
			//不等，则返回相关错误信息
			if(session.get("errorTable") != null) {
				session.replace("errorTable", "最小月份大于最大月份");
			}else {
				session.put("errorTable", "最小月份大于最大月份");
			}
			return ERROR;
		}
	}
	
	public String returnBack() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		if(session.get("table") != null) {
			session.remove("table");
		}
		if(session.get("errorTable") != null) {
			session.remove("errorTable");
		}
		return SUCCESS;
	}
	
}