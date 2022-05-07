package com.print;

public class BuyValue implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer purchaseValue = 5;
	private Integer discountValue = 10;
	private Double discount = 0.95;
	
	public BuyValue() {}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPurchaseValue() {
		return purchaseValue;
	}

	public Integer getDiscountValue() {
		return discountValue;
	}

	public Double getDiscount() {
		return discount;
	}
	
}
