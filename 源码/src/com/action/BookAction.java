package com.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.entity.Books;
import com.entity.Imports;
import com.entity.Shops;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.print.BookItems;
import com.service.BookService;
import com.service.ImportService;
import com.service.ShopService;

@Controller
@Scope("prototype")
public class BookAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Autowired private BookService bookService;
	@Autowired private ShopService shopService;
	@Autowired private ImportService importService;
	private Books book = new Books();
	private String id = null;
	private Map<String, Object> session;
	
	public Books getBook() {
		return book;
	}
	
	public void setBook(Books book) {
		this.book = book;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//搜索图书
	public String searchBooks() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		Books booktemp = bookService.BookByNumber(book.getBnumber());
		if(booktemp != null) {
			List<Imports> imports = importService.ShopByBook(book.getBnumber());
			List<BookItems> bookitems = new ArrayList<>();
			Iterator<Imports> it = imports.iterator();
			while(it.hasNext()) {
				Imports port = it.next();
				if(port.getInumgoods() < 0) {
					continue;
				}
				BookItems bookitem = new BookItems();
				bookitem.setBnumber(booktemp.getBnumber());
				bookitem.setBname(booktemp.getBname());
				bookitem.setBwriter(booktemp.getBwriter());
				bookitem.setBpublish(booktemp.getBpublish());
				bookitem.setBprice(booktemp.getBprice());
				Shops shop = shopService.ShopByUsername(port.getKey().getIusername());
				bookitem.setBnowprice(booktemp.getBprice() * port.getIdiscount());
				bookitem.setSusername(shop.getSusername());
				bookitem.setSshopname(shop.getSshopname());
				bookitem.setSphone(shop.getSphone());
				bookitem.setSlocal(shop.getSlocal());
				bookitem.setIgoodsnumber(port.getInumgoods());
				bookitems.add(bookitem);
			}
			if(session.get("bookitems") != null) {
				session.replace("bookitems", bookitems);
			}else {
				session.put("bookitems", bookitems);
			}
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	//查看书籍详细信息
	public String bookMessage() {
		book = bookService.BookByNumber(id);
		return "success";
	}
	
	//图书基本信息导入
	public String insertBook() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String errorBook = null; //记录图书导入错误信息
		//检查图书出版号是否符合标准
		if((new CheckMessage()).isBookISBN(book.getBnumber()) == false) {
			//不符合标准，存入会话，并跳转错误界面
			errorBook = "ISBN号不符合标准";
			if(session.get("errorBook") != null) {
				session.replace("errorBook", errorBook);
			}else {
				session.put("errorBook", errorBook);
			}
			return ERROR;
		}else {
			//此处是为消除前一次记录的影响
			//在格式正确时，删除错误信息会话
			if(session.get("errorBook") != null) {
				session.remove("errorBook");
			}
		}
		//导入新书信息
		bookService.insertBook(book.getBnumber(), book.getBname(),
				book.getBwriter(), book.getBpublish(), book.getBprice());
		return SUCCESS;
	}
	
}
