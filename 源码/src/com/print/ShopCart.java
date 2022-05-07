package com.print;

public class ShopCart implements java.io.Serializable, Comparable<ShopCart> {
	private static final long serialVersionUID = 1L;
	
	private Long Onumber;
	private String Bnumber;
	private String Bname;
	private String Sshopname;
	private Integer Oquantity;
	private Double Bnowprice;
	private String Sphone;
	
	public ShopCart() {}

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

	public Integer getOquantity() {
		return Oquantity;
	}

	public void setOquantity(Integer oquantity) {
		Oquantity = oquantity;
	}

	public Double getBnowprice() {
		return Bnowprice;
	}

	public void setBnowprice(Double bnowprice) {
		Bnowprice = bnowprice;
	}

	public String getSphone() {
		return Sphone;
	}

	public void setSphone(String sphone) {
		Sphone = sphone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override  
	public int compareTo(ShopCart o) {  
	    int i = (int) (this.getOnumber() - o.getOnumber());//先按照年龄排序  
	    if(i == 0){  
	        return this.getBnumber().compareTo(o.getBnumber());//如果年龄相等了再用分数进行排序  
	    }  
	    return i;  
	}
	
}
