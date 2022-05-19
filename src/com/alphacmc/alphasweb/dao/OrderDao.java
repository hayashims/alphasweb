package com.alphacmc.alphasweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alphacmc.alphasweb.bean.OrderBean;

public class OrderDao extends BaseDao<OrderBean> {

    @Override
    protected OrderBean setBean(ResultSet rset) {
    	OrderBean orderBean = new OrderBean();
        try {
        	orderBean.setOrderId(rset.getInt("order_id"));
        	orderBean.setOrderDate(rset.getTimestamp("order_date"));
        	orderBean.setCustomerName(rset.getString("customer_name"));
        	orderBean.setProdName(rset.getString("prod_name"));
        	orderBean.setPrice(rset.getInt("price"));
        	orderBean.setQty(rset.getInt("qty"));
        	orderBean.setQty(rset.getInt("amount"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            orderBean = null;
        }
        return orderBean;
    }

}
