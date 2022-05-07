package com.entity;

import org.hibernate.annotations.Entity;

@SuppressWarnings("deprecation")
@Entity
public class Customers implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String Cusername;
	private String Cname;
	private String Cpassword;
	private String Csex;
	private String Cphone;
	private String Clocal;
	private Integer Cvalue;
	
	public Customers() {}
	
	public Customers(String Cusername, String Cname, String Cpassword, String Csex,
			String Cphone, String Clocal, Integer Cvalue) {
		this.Cusername = Cusername;
		this.Cname = Cname;
		this.Cpassword = Cpassword;
		this.Csex = Csex;
		this.Cphone = Cphone;
		this.Clocal = Clocal;
		this.Cvalue = Cvalue;
	}

	public String getCusername() {
		return Cusername;
	}

	public void setCusername(String cusername) {
		Cusername = cusername;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getCpassword() {
		return Cpassword;
	}

	public void setCpassword(String cpassword) {
		Cpassword = cpassword;
	}

	public String getCsex() {
		return Csex;
	}

	public void setCsex(String csex) {
		Csex = csex;
	}

	public String getCphone() {
		return Cphone;
	}

	public void setCphone(String cphone) {
		Cphone = cphone;
	}

	public String getClocal() {
		return Clocal;
	}

	public void setClocal(String clocal) {
		Clocal = clocal;
	}

	public Integer getCvalue() {
		return Cvalue;
	}

	public void setCvalue(Integer cvalue) {
		Cvalue = cvalue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
