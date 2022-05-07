package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.entity.Customers;

@Repository("customerDao")
public class CustomerDao implements UserOperator {
	@Resource SessionFactory factory;
	//Ôö
	public void addCustomer(Customers customer) {
		factory.getCurrentSession().save(customer);
	}
	//É¾
	public void deleteCustomer(Customers customer) {
		factory.getCurrentSession().delete(customer);
	}
	//¸Ä
	public void updateCustomer(Customers customer) {
		factory.getCurrentSession().merge(customer);
	}
	//²é
	@Override @SuppressWarnings("deprecation")
	public Customers getByUsername(String cusername) {
		return (Customers) factory.getCurrentSession().createSQLQuery("select * from T_Customers where Cusername=:Cusername")
				.addEntity(Customers.class).setString("Cusername", cusername).uniqueResult();
	}
	
	@Override @SuppressWarnings("deprecation")
	public Customers getByUsernameAndPassword(String cusername, String cpassword) {
		return (Customers) factory.getCurrentSession().createSQLQuery("select * from T_Customers where Cusername=:Cusername and Cpassword=:Cpassword")
				.addEntity(Customers.class).setString("Cusername", cusername).setString("Cpassword", cpassword).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Customers> getUserList(){
		return (List<Customers>) factory.getCurrentSession().createSQLQuery("select * from T_Customers")
				.addEntity(Customers.class).list();
	}
	
}
