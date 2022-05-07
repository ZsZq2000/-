package com.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import com.entity.Orders;
import com.print.TableShow;

@Repository("ordderDao")
public class OrderDao {
	@Resource SessionFactory factory;
	//增
	public void addOrder(Orders order) {
		factory.getCurrentSession().save(order);
	}
	//删
	public void deleteOrder(Orders order) {
		factory.getCurrentSession().delete(order);
	}
	//改
	public void updateOrder(Orders order) {
		factory.getCurrentSession().merge(order);
	}
	//查
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Orders> getCustomerOrders(String cusername){
		String hql = "select * from T_Orders where Cusername=:Cusername and "
				+ "Opay=:Opay";
		return (List<Orders>) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Orders.class).setString("Cusername", cusername).setBoolean("Opay", true).list();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Orders> getCustomerPreOrders(String cusername){
		String hql="select * from T_Orders where Cusername=:Cusername"
				+ " and Opay=:Opay";
		return (List<Orders>) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Orders.class).setString("Cusername", cusername).setBoolean("Opay", false).list();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Orders> getShopOrders(String susername){
		String hql = "select * from T_Orders where Susername=:Susername and "
				+ "Opay=:Opay";
		return (List<Orders>) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Orders.class).setString("Susername", susername).setBoolean("Opay", true).list();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Orders> getOrder(String cusername, String susername, String bnumber) {
		String hql = "select * from T_Orders where Cusername=:Cusername and Susername=:Susername"
				+ " and Bnumber=:Bnumber";
		return (List<Orders>) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Orders.class).setString("Cusername", cusername).setString("Susername", susername)
				.setString("Bnumber", bnumber).list();
	}
	
	public Orders getOrder(Long onumber) {
		return factory.getCurrentSession().find(Orders.class, onumber);
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public List<TableShow> getTable(String susername, Date postdate, Date nowdate) {
		String hql = "select Bnumber, sum(Osumprice) as hello, sum(Oquantity) as hi from T_Orders where Otime>=:Opost and Otime<=:Onow "
				+ "and Susername=:Susername and Oaccept=:Oaccept and Oreturn=:Oreturn group by Bnumber";
		List list = (List) factory.getCurrentSession().createSQLQuery(hql)
				.addScalar("Bnumber",StandardBasicTypes.STRING).addScalar("hello", StandardBasicTypes.DOUBLE).addScalar("hi", StandardBasicTypes.INTEGER).setDate("Opost", new Timestamp(postdate.getTime())).setDate("Onow", new Timestamp(nowdate.getTime()))
				.setString("Susername", susername).setBoolean("Oaccept", true).setBoolean("Oreturn", false).list();
		List<TableShow> tableshows = new ArrayList<>();
		for(Iterator iterator = list.iterator();iterator.hasNext();){
			TableShow tableshow = new TableShow();
			Object[] Objects = (Object[]) iterator.next();
			tableshow.setBnumber((String) Objects[0]);
			tableshow.setOsumprice((Double) Objects[1]);
			tableshow.setOquantity((Integer) Objects[2]);
			tableshows.add(tableshow);
		}
		return tableshows;
	}
	
}
