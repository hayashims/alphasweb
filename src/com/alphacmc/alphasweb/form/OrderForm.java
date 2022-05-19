package com.alphacmc.alphasweb.form;

import java.sql.Timestamp;

public class OrderForm {

	// 注文ID
	private String orderId;

	// 注文日時
	private Timestamp orderDate;

	// 顧客ID
	private String customerId;

	// 商品ID
	private String prodId;

	// 数量
	private String qty;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}


}
