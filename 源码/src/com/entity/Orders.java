package com.entity;

import java.util.Date;

import org.hibernate.annotations.Entity;

@SuppressWarnings("deprecation")
@Entity
public class Orders implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long Onumber;
	private Customers customers = new Customers();
	private Shops shops = new Shops();
	private Books books = new Books();
	private String Olocal;
	private Date Otime;
	private Integer Oquantity;
	private Double Osumprice;
	private String Oremark;
	private Boolean Opay;
	private Boolean Oaccept;
	private Boolean Oreturn;
	private String Oreason;
	private Boolean Ocancel;
	private Boolean Odiscount;
	private Integer Ogetvalue;
	
	public Orders() {}
	
	public Orders(Long Onumber, Customers customers, Shops shops, Books books,
			String Olocal, Date Otime,Integer Oquantity, Double Osumprice, String Oremark,
			Boolean Opay, Boolean Oaccept, Boolean Oreturn, String Oreason,
			Boolean Ocancel, Boolean Odiscount, Integer Ogetvalue) {
		this.Onumber = Onumber;
		this.customers = customers;
		this.shops = shops;
		this.books = books;
		this.Olocal = Olocal;
		this.Otime = Otime;
		this.Oquantity = Oquantity;
		this.Osumprice = Osumprice;
		this.Oremark = Oremark;
		this.Opay = Opay;
		this.Oaccept = Oaccept;
		this.Oreturn = Oreturn;
		this.Oreason = Oreason;
		this.Ocancel = Ocancel;
		this.Odiscount = Odiscount;
		this.Ogetvalue = Ogetvalue;
	}

	public Long getOnumber() {
		return Onumber;
	}

	public void setOnumber(Long onumber) {
		Onumber = onumber;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public Shops getShops() {
		return shops;
	}

	public void setShops(Shops shops) {
		this.shops = shops;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public String getOlocal() {
		return Olocal;
	}

	public void setOlocal(String olocal) {
		Olocal = olocal;
	}

	public Date getOtime() {
		return Otime;
	}

	public void setOtime(Date otime) {
		Otime = otime;
	}

	public Integer getOquantity() {
		return Oquantity;
	}

	public void setOquantity(Integer oquantity) {
		Oquantity = oquantity;
	}

	public Double getOsumprice() {
		return Osumprice;
	}

	public void setOsumprice(Double osumprice) {
		Osumprice = osumprice;
	}
	
	public String getOremark() {
		return Oremark;
	}

	public void setOremark(String oremark) {
		Oremark = oremark;
	}

	public Boolean getOpay() {
		return Opay;
	}

	public void setOpay(Boolean opay) {
		Opay = opay;
	}

	public Boolean getOaccept() {
		return Oaccept;
	}

	public void setOaccept(Boolean oaccept) {
		Oaccept = oaccept;
	}

	public Boolean getOreturn() {
		return Oreturn;
	}

	public void setOreturn(Boolean oreturn) {
		Oreturn = oreturn;
	}
	
	public String getOreason() {
		return Oreason;
	}

	public void setOreason(String oreason) {
		Oreason = oreason;
	}

	public Boolean getOcancel() {
		return Ocancel;
	}

	public void setOcancel(Boolean ocancel) {
		Ocancel = ocancel;
	}

	public Boolean getOdiscount() {
		return Odiscount;
	}

	public void setOdiscount(Boolean odiscount) {
		Odiscount = odiscount;
	}

	public Integer getOgetvalue() {
		return Ogetvalue;
	}

	public void setOgetvalue(Integer ogetvalue) {
		Ogetvalue = ogetvalue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
