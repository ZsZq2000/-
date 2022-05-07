package com.entity;

public class CompositionKey implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String Iusername;
	private String Inumber;
	
	public CompositionKey() {}
	
	public CompositionKey(String Susername, String Bnumber) {
		this.Iusername = Susername;
		this.Inumber = Bnumber;
	}
	
	public String getIusername() {
		return Iusername;
	}
	
	public void setIusername(String susername) {
		Iusername = susername;
	}
	
	public String getInumber() {
		return Inumber;
	}
	
	public void setInumber(String bnumber) {
		Inumber = bnumber;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
