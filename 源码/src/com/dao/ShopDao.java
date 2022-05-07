package com.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.entity.Shops;

@Repository("shopDao")
public class ShopDao implements UserOperator {
	@Resource SessionFactory factory;
	//Ôö
	public void addShop(Shops shop) {
		factory.getCurrentSession().save(shop);
	}
	//É¾
	public void deleteShop(Shops shop) {
		factory.getCurrentSession().delete(shop);
	}
	public void updateShop(Shops shop) {
		factory.getCurrentSession().merge(shop);
	}
	//²é
	@SuppressWarnings("deprecation")
	@Override
	public Shops getByUsername(String susername) {
		// TODO Auto-generated method stub
		String hql = "select * from T_Shops where Susername=:Susername";
		return (Shops) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Shops.class).setString("Susername", susername).uniqueResult();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Object getByUsernameAndPassword(String susername, String spassword) {
		// TODO Auto-generated method stub
		String hql = "select * from T_Shops where Susername=? and Spassword=?";
		return factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Shops.class).setString(0, susername).setString(1, spassword).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Shops> getUserList(){
		String hql="select * from T_Shops";
		return (List<Shops>) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Shops.class).list();
	}
	
}
