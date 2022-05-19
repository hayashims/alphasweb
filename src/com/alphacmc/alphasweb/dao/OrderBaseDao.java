package com.alphacmc.alphasweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alphacmc.alphasweb.bean.OrderBean;

public class OrderBaseDao extends BaseDao<OrderBean> {

    @Override
    protected OrderBean setBean(ResultSet rset) {
    	OrderBean orderBean = new OrderBean();
        try {
        	orderBean.setOrderId(rset.getInt("order_id"));
        	orderBean.setOrderDate(rset.getTimestamp("order_date"));
        	orderBean.setCustomerId(rset.getInt("customer_id"));
        	orderBean.setProdId(rset.getInt("prod_id"));
        	orderBean.setQty(rset.getInt("qty"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            orderBean = null;
        }
        return orderBean;
    }

}
