package com.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.service.CustomerService;

public class CustomerTest {
	public void addTest() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerService customerService = (CustomerService) ctx.getBean("customerService");
		customerService.Register("haha", "haha", "123456", "ÄÐ", "12345678901", "Zhejiang");
	}
	@Test
	public void updateTest() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerService customerService = (CustomerService) ctx.getBean("customerService");
		customerService.Register("haha", "haha", "123456", "ÄÐ", "12345678901", "Zhejiang");
		customerService.ChangePassword("haha", "654321");
		customerService.checkDiscount("haha");
	}
}
