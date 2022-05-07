package com.entity;

import org.hibernate.annotations.Entity;

@SuppressWarnings("deprecation")
@Entity
public class Books implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String Bnumber;
	private String Bname;
	private String Bwriter;
	private String Bpublish;
	private Double Bprice;
	
	public Books() {}
	
	public Books(String Bnumber, String Bname, String Bwriter, String Bpublish,
			Double Bprice) {
		this.Bnumber = Bnumber;
		this.Bname = Bname;
		this.Bwriter = Bwriter;
		this.Bpublish = Bpublish;
		this.Bprice = Bprice;
	}

	public String getBnumber() {
		return Bnumber;
	}

	public void setBnumber(String bnumber) {
		Bnumber = bnumber;
	}

	public String getBname() {
		return Bname;
	}

	public void setBname(String bname) {
		Bname = bname;
	}

	public String getBwriter() {
		return Bwriter;
	}

	public void setBwriter(String bwriter) {
		Bwriter = bwriter;
	}

	public String getBpublish() {
		return Bpublish;
	}

	public void setBpublish(String bpublish) {
		Bpublish = bpublish;
	}

	public Double getBprice() {
		return Bprice;
	}

	public void setBprice(Double bprice) {
		Bprice = bprice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
