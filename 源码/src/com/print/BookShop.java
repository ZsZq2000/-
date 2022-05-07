package com.print;

public class BookShop implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String Bnumber;
	private String Bname;
	private Double Bprice;
	private Double Idiscount;
	private Integer Inumgoods;
	
	public BookShop() {}

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

	public Double getBprice() {
		return Bprice;
	}

	public void setBprice(Double bprice) {
		Bprice = bprice;
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
