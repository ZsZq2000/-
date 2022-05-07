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
	
	//����ͼ��
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
	
	//�鿴�鼮��ϸ��Ϣ
	public String bookMessage() {
		book = bookService.BookByNumber(id);
		return "success";
	}
	
	//ͼ�������Ϣ����
	public String insertBook() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String errorBook = null; //��¼ͼ�鵼�������Ϣ
		//���ͼ�������Ƿ���ϱ�׼
		if((new CheckMessage()).isBookISBN(book.getBnumber()) == false) {
			//�����ϱ�׼������Ự������ת�������
			errorBook = "ISBN�Ų����ϱ�׼";
			if(session.get("errorBook") != null) {
				session.replace("errorBook", errorBook);
			}else {
				session.put("errorBook", errorBook);
			}
			return ERROR;
		}else {
			//�˴���Ϊ����ǰһ�μ�¼��Ӱ��
			//�ڸ�ʽ��ȷʱ��ɾ��������Ϣ�Ự
			if(session.get("errorBook") != null) {
				session.remove("errorBook");
			}
		}
		//����������Ϣ
		bookService.insertBook(book.getBnumber(), book.getBname(),
				book.getBwriter(), book.getBpublish(), book.getBprice());
		return SUCCESS;
	}
	
}
