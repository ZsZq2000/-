package com.entity;

import org.hibernate.annotations.Entity;

@SuppressWarnings("deprecation")
@Entity
public class Imports implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private CompositionKey key = new CompositionKey();
	private Shops shops = new Shops();
	private Books books = new Books();
	private Double Idiscount;
	private Integer Inumgoods;
	
	public Imports() {}
	
	public Imports(Shops shops, Books books, Double Idiscount, Integer Inumgoods) {
		this.shops = shops;
		this.books = books;
		key.setIusername(shops.getSusername());
		key.setInumber(books.getBnumber());
		this.Idiscount = Idiscount;
		this.Inumgoods = Inumgoods;
	}
	
	public CompositionKey getKey() {
		return key;
	}

	public void setKey(CompositionKey key) {
		this.key = key;
	}

	public Shops getShops() {
		return shops;
	}

	public void setShops(Shops shops) {
		this.shops = shops;
		this.key.setIusername(shops.getSusername());
	}
	
	public void setShops(String susername) {
		this.shops.setSusername(susername);
		this.key.setIusername(susername);
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
		this.key.setInumber(books.getBnumber());
	}
	
	public void setBooks(String bnumber) {
		this.books.setBnumber(bnumber);
		this.key.setInumber(bnumber);
	}

	public Double getIdiscount() {
		return Idiscount;
	}

	public void setIdiscount(Double idiscount) {
		Idiscount = idiscount;
	}

	public Integer getInumgoods() {
		return Inumgoods;
	}

	public void setInumgoods(Integer inumgoods) {
		Inumgoods = inumgoods;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
