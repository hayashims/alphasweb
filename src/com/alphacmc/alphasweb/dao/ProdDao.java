package com.alphacmc.alphasweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alphacmc.alphasweb.bean.ProdBean;

public class ProdDao extends BaseDao<ProdBean> {

    @Override
    protected ProdBean setBean(ResultSet rset) {
    	ProdBean prodBean = new ProdBean();
        try {
        	prodBean.setProdId(rset.getInt("prod_id"));
        	prodBean.setProdName(rset.getString("prod_name"));
        	prodBean.setPrice(rset.getInt("price"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            prodBean = null;
        }
        return prodBean;
    }

}
