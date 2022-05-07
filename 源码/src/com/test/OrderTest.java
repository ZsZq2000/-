package com.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.service.BookService;
import com.service.CustomerService;
import com.service.OrderService;
import com.service.ShopService;

public class OrderTest {
	@SuppressWarnings("resource")
	@Test
	public void addTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerService customerService = (CustomerService) ctx.getBean("customerService");
		ShopService shopService = (ShopService) ctx.getBean("shopService");
		BookService bookService = (BookService) ctx.getBean("bookService");
		OrderService orderService = (OrderService) ctx.getBean("orderService");
		
		customerService.Register("haha", "haha", "123456", "ÄÐ", "12345678901", "Zhejiang");
		shopService.shopRegister("ZJUT121", "ZJUT", "123456", "Å®", "12345678912", "ZJUT", "ZJUT");
		bookService.insertBook("123456789009", "math", "haha", "ZJUT", 32.0);
		
		orderService.insertOrder("haha", "ZJUT121", "123456789009", "ZJUT", 1, 32.0, null, true, 32);
	}
	
}
