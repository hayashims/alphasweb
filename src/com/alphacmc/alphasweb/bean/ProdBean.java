package com.alphacmc.alphasweb.bean;

public class ProdBean {

    // 商品ID
    private int prodId;
    // 商品名
    private String prodName;
    // 商品価格
    private int price;
    public int getProdId() {
        return prodId;
    }
    public void setProdId(int prodId) {
        this.prodId = prodId;
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

}
