package com.alphacmc.alphasweb.bean;

public class OrderBean extends OrderBaseBean {

    // 顧客名
    private String customerName;
    //　商品名
    private String prodName;
    // 価格
    private int price;
    // 金額
    private int amount;
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
    

}
