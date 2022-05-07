package com.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.Imports;
import com.service.ImportService;

public class ImportTest {
	@SuppressWarnings("resource")
	@Test
	public void BookShow() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//CustomerService customerService = (CustomerService) ctx.getBean("customerService");
		//ShopService shopService = (ShopService) ctx.getBean("shopService");
		//BookService bookService = (BookService) ctx.getBean("bookService");
		ImportService importService = (ImportService) ctx.getBean("importService");
		
		//customerService.Register("haha", "hale", "123456", "ÄÐ", "12345678901", "Zhejiang");
		//shopService.Register("ZJUT121", "ZJUT", "123456", "Å®", "12345678912", "ZJUT", "ZJUT");
		//bookService.insertBook("123456789009", "math", "haha", "ZJUT", 32.0);
		
		//importService.insertImport("ZJUT121", "123456789009", 0.9, 100);
		List<Imports> books = importService.BookByShop("ZJUT121");
		Iterator<Imports> it = books.iterator();
		while(it.hasNext()) {
			Imports book = it.next();
			System.out.println(book.getBooks().getBnumber());
		}
	}
}
