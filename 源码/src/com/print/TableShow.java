package com.print;

public class TableShow implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String Bnumber;
	private Double Osumprice;
	private Integer Oquantity;
	
	public TableShow() {}

	public String getBnumber() {
		return Bnumber;
	}

	public void setBnumber(String bnumber) {
		Bnumber = bnumber;
	}

	public Double getOsumprice() {
		return Osumprice;
	}

	public void setOsumprice(Double osumprice) {
		Osumprice = osumprice;
	}

	public Integer getOquantity() {
		return Oquantity;
	}

	public void setOquantity(Integer oquantity) {
		Oquantity = oquantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
