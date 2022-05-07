package com.print;

public class OrderShop implements java.io.Serializable, Comparable<OrderShop> {
	private static final long serialVersionUID = 1L;
	
	private Long Onumber;
	private String Bnumber;
	private String Bname;
	private String Cusername;
	private String Cphone;
	private Integer Oquantity;
	private Double Osumprice;
	private String Olocal;
	private Boolean Osend;
	private Boolean Oback;
	
	public OrderShop() {}

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

	public String getCusername() {
		return Cusername;
	}

	public void setCusername(String cusername) {
		Cusername = cusername;
	}

	public String getCphone() {
		return Cphone;
	}

	public void setCphone(String cphone) {
		Cphone = cphone;
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

	public String getOlocal() {
		return Olocal;
	}

	public void setOlocal(String olocal) {
		Olocal = olocal;
	}

	public Boolean getOsend() {
		return Osend;
	}

	public void setOsend(Boolean osend) {
		Osend = osend;
	}

	public Boolean getOback() {
		return Oback;
	}

	public void setOback(Boolean oback) {
		Oback = oback;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override  
	public int compareTo(OrderShop o) {  
	    int i = (int) (this.getOnumber() - o.getOnumber());//先按照年龄排序  
	    if(i == 0){  
	        return this.getBnumber().compareTo(o.getBnumber());//如果年龄相等了再用分数进行排序  
	    }  
	    return i;  
	}

}
