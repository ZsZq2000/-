package com.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Books;

@Repository("bookDao")
@Transactional
public class BookDao {
	@Resource SessionFactory factory;
	//增
	public void addBook(Books book) {
		factory.getCurrentSession().save(book);
	}
	//改
	public void delectBook(Books book) {
		factory.getCurrentSession().delete(book);
	}
	//改
	public void updateBook(Books book) {
		factory.getCurrentSession().merge(book);
	}
	//查
	@SuppressWarnings("deprecation")
	public Books getByBooknumber(String bnumber) {
		// TODO Auto-generated method stub
		String hql = "select * from T_Books where Bnumber=:Bnumber";
		return (Books) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Books.class).setString("Bnumber", bnumber).uniqueResult();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Books> getByBookname(String bname) {
		String hql = "select * from T_Books where Bname=:Bname";
		return (List<Books>) factory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Books.class).setString("Bname", bname).list();
	}

}
