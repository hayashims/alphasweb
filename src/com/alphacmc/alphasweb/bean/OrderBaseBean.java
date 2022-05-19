package com.alphacmc.alphasweb.bean;

import java.sql.Timestamp;

public class OrderBaseBean {
    // 注文番号
    private int orderId;
    // 注文日時
    private Timestamp orderDate;
    // 顧客ID
    private int customerId;
    //　商品ID
    private int prodId;
    // 数量
    private int qty;
    
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
    
}
