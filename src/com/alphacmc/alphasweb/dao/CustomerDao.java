package com.alphacmc.alphasweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alphacmc.alphasweb.bean.CustomerBean;

public class CustomerDao extends BaseDao<CustomerBean> {

    @Override
    protected CustomerBean setBean(ResultSet rset) {
        CustomerBean customer = new CustomerBean();
        try {
            customer.setCustomerId(rset.getInt("customer_id"));
            customer.setCustomerName(rset.getString("customer_name"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            customer = null;
        }
        return customer;
    }

}
