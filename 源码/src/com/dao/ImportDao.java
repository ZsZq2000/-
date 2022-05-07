package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.entity.CompositionKey;
import com.entity.Imports;

@Repository("importDao")
public class ImportDao {
	@Resource SessionFactory factory;
	
	public void addImport(Imports imports) {
		factory.getCurrentSession().save(imports);
	}
	
	public void updateImport(Imports imports) {
		factory.getCurrentSession().merge(imports);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Imports> getByShop(String susername){
		String hql = "select * from T_Imports where Iusername=:Susername";
		return (List<Imports>) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Imports.class).setString("Susername", susername).list();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Imports> getByBook(String bnumber){
		String hql = "select * from T_Imports where Inumber=:Bnumber";
		return (List<Imports>) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Imports.class).setString("Bnumber", bnumber).list();
	}	
	
	public Imports getByBookandShop(String susername, String bnumber) {
		CompositionKey key = new CompositionKey(susername, bnumber);
		return factory.getCurrentSession().find(Imports.class, key);
	}
	
}
