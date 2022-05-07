package com.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.BookDao;
import com.entity.Books;

@Service("bookService")
public class BookService {
	@Resource private BookDao bookDao;
	public void insertBook(String bnumber, String bname, String bwriter,
			String bpublish, Double bprice) {
		Books book = new Books();
		book.setBnumber(bnumber);
		book.setBname(bname);
		book.setBwriter(bwriter);
		book.setBpublish(bpublish);
		book.setBprice(bprice);
		bookDao.addBook(book);
	}
	
	public Books BookByNumber(String bnumber) {
		return bookDao.getByBooknumber(bnumber);
	}
	
}
