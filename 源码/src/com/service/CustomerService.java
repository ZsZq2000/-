package com.service;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CustomerDao;
import com.entity.Customers;

@Service("customerService")
@Transactional
public class CustomerService implements ServiceOperator {
	@Resource private CustomerDao customerDao;
	
	public boolean checkCustomer(String cusername) {
		return customerDao.getByUsername(cusername) != null;
	}
	
	public boolean checkCustomer(String cusername, String cpassword) {
		return customerDao.getByUsernameAndPassword(cusername, cpassword) != null;
	}
	
	public void Register(String cusername, String cname, String cpassword, String csex, 
			String cphone, String clocal) {
		Customers customer = new Customers();
		customer.setCusername(cusername);
		customer.setCname(cname);
		customer.setCpassword(cpassword);
		customer.setCsex(csex);
		customer.setCphone(cphone);
		customer.setClocal(clocal);
		customer.setCvalue(0);
		customerDao.addCustomer(customer);
	}
	//修改密码
	public void ChangePassword(String cusername, String cpassword) {
		Customers customer = customerDao.getByUsername(cusername);
		customer.setCpassword(cpassword);
		customerDao.updateCustomer(customer);
		List<Customers> customers = customerDao.getUserList();
		Iterator<Customers> it = customers.iterator();
		while(it.hasNext()) {
			customer = it.next();
			System.out.println(customer.getCusername()+" "+customer.getCpassword());
		}
	}
	
	public void saveCustomer(Customers customer) {
		customerDao.updateCustomer(customer);
	}
	
	//查看积分
	public void checkDiscount(String cusername) {
		Customers customer = customerDao.getByUsername(cusername);
		System.out.println(customer.getCvalue()+" "+customer.getCsex());
	}
	
	public Customers CustomerByUsername(String cusername) {
		return customerDao.getByUsername(cusername);
	}
	
}
