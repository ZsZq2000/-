package com.print;

public class BookItems implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String Bnumber;
	private String Bname;
	private String Bwriter;
	private String Bpublish;
	private Double Bprice;
	private Double Bnowprice;
	private String Susername;
	private String Sphone;
	private String Sshopname;
	private String Slocal;
	private Integer Igoodsnumber;
	
	public BookItems() {}
	
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
	
	public Double getBnowprice() {
		return Bnowprice;
	}
	
	public void setBnowprice(Double bnowprice) {
		Bnowprice = bnowprice;
	}
	
	public String getSusername() {
		return Susername;
	}

	public void setSusername(String susername) {
		Susername = susername;
	}

	public String getSphone() {
		return Sphone;
	}
	
	public void setSphone(String sphone) {
		Sphone = sphone;
	}
	
	public String getSshopname() {
		return Sshopname;
	}
	
	public void setSshopname(String sshopname) {
		Sshopname = sshopname;
	}
	
	public String getSlocal() {
		return Slocal;
	}
	
	public void setSlocal(String slocal) {
		Slocal = slocal;
	}
	
	public Integer getIgoodsnumber() {
		return Igoodsnumber;
	}
	
	public void setIgoodsnumber(Integer igoodsnumber) {
		Igoodsnumber = igoodsnumber;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
