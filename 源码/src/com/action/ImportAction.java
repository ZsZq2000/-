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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.print.BookShop;
import com.service.BookService;
import com.service.ImportService;
import com.service.ShopService;

@Controller
@Scope("prototype")
public class ImportAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Autowired ImportService importService;
	@Autowired ShopService shopService;
	@Autowired BookService bookService;
	private Imports imports;
	private Map<String, Object> session;
	private String id = null;
	
	public Imports getImports() {
		return imports;
	}

	public void setImports(Imports imports) {
		this.imports = imports;
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
	//ͼ�����
	public String bookManage() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String username = (String) session.get("Susername");
		List<Imports> importemps = importService.BookByShop(username);
		List<BookShop> bookshops = new ArrayList<>();
		Books book = new Books();
		Iterator<Imports> it = importemps.iterator();
		while(it.hasNext()) {
			Imports importemp = it.next();
			if(importemp.getInumgoods() < 0) {
				continue;
			}
			BookShop bookshop = new BookShop();
			book = bookService.BookByNumber(importemp.getKey().getInumber());
			bookshop.setBnumber(book.getBnumber());
			bookshop.setBname(book.getBname());
			bookshop.setBprice(book.getBprice());
			bookshop.setIdiscount(importemp.getIdiscount());
			bookshop.setInumgoods(importemp.getInumgoods());
			bookshops.add(bookshop);
		}
		if(session.get("bookshop") != null) {
			session.replace("bookshop", bookshops);
		}else {
			session.put("bookshop", bookshops);
		}
		return SUCCESS;
	}

	public String changeBook() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String username = (String) session.get("Susername");
		imports = importService.oneImports(username, id);
		if(session.get("bnumber") != null) {
			session.replace("bnumber", id);
		}else {
			session.put("bnumber", id);
		}
		return SUCCESS;
	}
	
	public String deleteBook() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String username = (String) session.get("Susername");
		imports = importService.oneImports(username, id);
		imports.setInumgoods(-1000000);
		importService.saveImport(imports);
		session.remove("bnumber");
		return SUCCESS;
	}
	
	public String changeDicount() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String username = (String) session.get("Susername");
		String number = (String) session.get("bnumber");
		Imports importemp = importService.oneImports(username, number);
		importemp.setIdiscount(imports.getIdiscount());
		importService.saveImport(importemp);
		session.remove("bnumber");
		return SUCCESS;
	}
	
	public String changeNumgoods() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String username = (String) session.get("Susername");
		String number = (String) session.get("bnumber");
		Imports importemp = importService.oneImports(username, number);
		importemp.setInumgoods(imports.getInumgoods());
		importService.saveImport(importemp);
		session.remove("bnumber");
		return SUCCESS;
	}
	
	//ͼ�����
	public String addBook() {
		//����id��¼ͼ������
		//������������Ƿ����
		if(bookService.BookByNumber(id) == null) {
			//�����ڣ���תͼ�������Ϣ����ģ��
			return "fail";
		}else {
			ActionContext context = ActionContext.getContext();
			session = (Map<String, Object>) context.getSession();
			//��ȡ��ǰ��Ա�û���
			String username = (String) session.get("Susername");
			//�鿴�õ��Ƿ��������۸�ͼ��
			Imports importemp = importService.oneImports(username, id);
			if(importemp == null) {
				//�����ڣ�������µ�ͼ��������Ϣ
				importService.insertImport(username, id, imports.getIdiscount(),
						imports.getInumgoods());
			}else {
				//���ڣ��������޸����ֵ
				importemp.setIdiscount(imports.getIdiscount());
				importemp.setInumgoods(imports.getInumgoods());
				importService.saveImport(importemp);
			}
			return SUCCESS;
		}
	}
	
}
