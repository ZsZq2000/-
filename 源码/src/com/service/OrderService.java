package com.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OrderDao;
import com.entity.Orders;
import com.print.TableShow;


@Service("orderService")
@Transactional
public class OrderService {
	@Resource private OrderDao orderDao;
	public void insertOrder(String cusername, String susername, String bnumber,
			String olocal, Integer oquantity, Double osumprice,
			String oremark, Boolean odiscount, Integer ogetvalue) {
		Orders order = new Orders();
		order.getCustomers().setCusername(cusername);
		order.getShops().setSusername(susername);
		order.getBooks().setBnumber(bnumber);
		order.setOlocal(olocal);
		Timestamp otime = new Timestamp(System.currentTimeMillis());
		order.setOtime(otime);
		order.setOquantity(oquantity);
		order.setOsumprice(osumprice);
		order.setOremark(oremark);
		order.setOpay(false);
		order.setOdiscount(odiscount);
		order.setOgetvalue(ogetvalue);
		order.setOaccept(false);
		order.setOreturn(false);
		order.setOreason(null);
		order.setOcancel(false);
		orderDao.addOrder(order);
	}
	
	public void saveOrder(Orders order) {
		orderDao.updateOrder(order);
	}
	
	public Long isExtraOrder(String cusername, String susername, String bnumber) {
		List<Orders> orders = orderDao.getOrder(cusername, susername, bnumber);
		Iterator<Orders> it = orders.iterator();
		while(it.hasNext()) {
			Orders order = it.next();
			if(order != null && order.getOpay() == false) {
				return order.getOnumber();
			}
		}
		return (long) -1;
	}
	
	public void changeGoodsNum(Long onumber, Integer num) {
		Orders order = orderDao.getOrder(onumber);
		order.setOquantity(order.getOquantity() + num);
		orderDao.updateOrder(order);
	}
	
	public List<Orders> showCart(String cusername) {
		return orderDao.getCustomerPreOrders(cusername);
	}
	
	public List<Orders> showOrder(String cusername){
		return orderDao.getCustomerOrders(cusername);
	}
	
	public Orders OrderByNumber(Long onumber) {
		return orderDao.getOrder(onumber);
	}
	
	public void dropOrder(Long onumber) {
		Orders order = orderDao.getOrder(onumber);
		orderDao.deleteOrder(order);
	}
	
	public List<Orders> checkOrder(String susername){
		return orderDao.getShopOrders(susername);
	}
	
	public List<TableShow> submitTable(String susername, Date postdate, Date nowdate) {
		return orderDao.getTable(susername, postdate, nowdate);
	}
	
}
