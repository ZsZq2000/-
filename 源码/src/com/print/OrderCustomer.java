package com.print;

public class OrderCustomer implements java.io.Serializable, Comparable<OrderCustomer> {
	private static final long serialVersionUID = 1L;
	
	private Long Onumber;
	private String Bnumber;
	private String Bname;
	private String Sshopname;
	private String Sphone;
	private Integer Oquantity;
	private Double Osumprice;
	private String Oaccept;
	private String Ocancel;
	
	public OrderCustomer() {}

	public Long getOnumber() {
		return Onumber;
	}

	public void setOnumber(Long onumber) {
		Onumber = onumber;
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

	public String getSshopname() {
		return Sshopname;
	}

	public void setSshopname(String sshopname) {
		Sshopname = sshopname;
	}

	public String getSphone() {
		return Sphone;
	}

	public void setSphone(String sphone) {
		Sphone = sphone;
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

	public String getOaccept() {
		return Oaccept;
	}

	public void setOaccept(String oaccept) {
		Oaccept = oaccept;
	}

	public String getOcancel() {
		return Ocancel;
	}

	public void setOcancel(String ocancel) {
		Ocancel = ocancel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override  
	public int compareTo(OrderCustomer o) {  
	    int i = (int) (this.getOnumber() - o.getOnumber());//先按照年龄排序  
	    if(i == 0){  
	        return this.getBnumber().compareTo(o.getBnumber());//如果年龄相等了再用分数进行排序  
	    }  
	    return i;  
	}
	
}
