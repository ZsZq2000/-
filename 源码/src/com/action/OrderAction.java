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
	//????????????????
	public String bookBuy() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		//??????????????????
		String username = (String) session.get("Cusername");
		//????????????????????????????????????
		if(username == null) {
			return "nologin";
		}else {
			//bookitem??????????????????????????????
			//id??????????????????
			//??????????????????bookitem??????????????????????????????
			@SuppressWarnings("unchecked")
			List<BookItems> bookitems = (List<BookItems>) session.get("bookitems");
			Iterator<BookItems> it = bookitems.iterator();
			BookItems bookitem = null;
			while(it.hasNext()) {
				bookitem = it.next();
				//??????????????????????????????????
				if(bookitem.getSusername().equals(id)) {
					break;
				}
			}
			//????????????????????????????????????0??????????ERROR????????
			if(bookitem.getIgoodsnumber() <= 0) {
				return ERROR;
			}else {
				//????????????????
				//????????????????????
				Long onum = orderService.isExtraOrder(username, bookitem.getSusername(),
						bookitem.getBnumber());
				//??????????????????????????1
				if(onum >= 0) {
					orderService.changeGoodsNum(onum, 1);
				}else {
					//????????????????????????????
					orderService.insertOrder(username, bookitem.getSusername(),
							bookitem.getBnumber(), "Internet", 1, bookitem.getBnowprice(),
							null, false, 0);
				}
				//??????????????????
				importService.changeGoodsNumber(bookitem.getSusername(),
						bookitem.getBnumber(), -1);
				return SUCCESS;
			}
		}
	}
	//??????????????
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
		//cart??????????????
		//Imports??????
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
			//????-1
			importService.changeGoodsNumber(imports.getKey().getIusername(),
					imports.getKey().getInumber(), -1);
			//??????+1
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
	
	//????????????
	public String purchaseOrder() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		//????????Onumber??????????????????
		//??????????????????????????????????
		if(session.get("Onumber") != null) {
			session.replace("Onumber", num); //????????????????????????
		}else {
			session.put("Onumber", num); //????????????????????????????
		}
		//??????????
		bookorder = orderService.OrderByNumber(num);
		Books book = bookService.BookByNumber(bookorder.getBooks().getBnumber());
		//??????????????????????????????(????????????)
		Imports imports = importService.oneImports(bookorder.getShops().getSusername(),
				bookorder.getBooks().getBnumber());
		//????????????????????????
		bookorder.setOsumprice(bookorder.getOquantity() * book.getBprice() * imports.getIdiscount());
		bookorder.setOgetvalue(bookorder.getOquantity() * buyvalue.getPurchaseValue());
		orderService.saveOrder(bookorder); //????????
		return SUCCESS;
	}
	
	//??????????????????????
	public String buyCertain() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		//????????????Onumber??????????????
		bookorder = orderService.OrderByNumber((Long)(session.get("Onumber")));
		//??????????????????????????????
		String username = (String) session.get("Cusername");
		Customers customer = customerService.CustomerByUsername(username);
		//????????
		//num??1??????????????????????0??????????????????
		if(num == (long)1) {
			bookorder.setOlocal(id);
		}else {
			bookorder.setOlocal(customer.getClocal());
		}
		//????????????
		if(pay == 1) {
			//????????????????
			Integer needvalue = (buyvalue.getDiscountValue() *
					bookorder.getOquantity()); //????????????
			//????????????????????????
			if(customer.getCvalue() < needvalue) {
				return ERROR;
			}else {
				//????????????????????????????????true??????????????????????????????
				//??????????????
				bookorder.setOdiscount(true);
				customer.setCvalue(customer.getCvalue() - needvalue);
				bookorder.setOsumprice(bookorder.getOsumprice() *
						buyvalue.getDiscount());
			}
		}
		//????????????????????????????
		customer.setCvalue(customer.getCvalue() + bookorder.getOgetvalue());
		customerService.saveCustomer(customer); //????????????
		bookorder.setOpay(true); //????????????????
		//????????????
		Timestamp otime = new Timestamp(System.currentTimeMillis());
		bookorder.setOtime(otime);
		orderService.saveOrder(bookorder); //????????
		session.remove("Onumber"); //??????????????????????????(????????)
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
					ordercustomer.setOaccept("??????");
				}else {
					ordercustomer.setOaccept("??????");
				}
				if(order.getOcancel() == true) {
					ordercustomer.setOcancel("??????");
				}else {
					ordercustomer.setOcancel("??????");
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
	
	//????????
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
	
	//????????
	public String acceptOrder() {
		//num????????????????
		//????????????
		bookorder = orderService.OrderByNumber(num);
		bookorder.setOaccept(true); //??????????????????
		orderService.saveOrder(bookorder); //????????
		return SUCCESS;	
	}
	
	//????????
	public String returnOrder() {
		//num????????????????
		//????????????
		bookorder = orderService.OrderByNumber(num);
		bookorder.setOcancel(true); //??????????????????
		//????????????????????????
		Customers customer = customerService.CustomerByUsername(bookorder.getCustomers().getCusername());
		Integer temp = 0;
		//??????????????????????????temp=1
		if(bookorder.getOdiscount() == true) {
			temp = 1;
		}
		//????????????
		customer.setCvalue(customer.getCvalue() - bookorder.getOgetvalue()
				+ buyvalue.getDiscountValue() * bookorder.getOquantity() * temp);
		customerService.saveCustomer(customer); //????????????
		//??????????????????
		importService.changeGoodsNumber(bookorder.getShops().getSusername(),
				bookorder.getBooks().getBnumber(), bookorder.getOquantity());
		orderService.saveOrder(bookorder); //????????
		return SUCCESS;
	}
	
	//????????
	public String refuseOrder() {
		//num????????????????
		//????????????
		bookorder = orderService.OrderByNumber(num);
		bookorder.setOreturn(false); //????????????????????
		orderService.saveOrder(bookorder); //????????
		return SUCCESS;
	}
	
	//??????????????
	public String showTable() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		//??????????????????
		String username = (String) session.get("Susername");
		//????????????????????????????????
		if(postdate.getTime() <= nowdate.getTime()) {
			//????????????????????
			List<TableShow> tableshows = orderService.submitTable(username, postdate, nowdate);
			//????????????????????
			if(session.get("table") != null) {
				session.replace("table", tableshows);
			}else {
				session.put("table", tableshows);
			}
			return SUCCESS;
		}else{
			//????????????????????????
			if(session.get("errorTable") != null) {
				session.replace("errorTable", "????????????????????");
			}else {
				session.put("errorTable", "????????????????????");
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