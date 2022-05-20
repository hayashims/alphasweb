package com.alphacmc.alphasweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alphacmc.alphasweb.bean.UserBean;

public class UserDao extends BaseDao<UserBean> {

    @Override
    protected UserBean setBean(ResultSet rset) {
    	UserBean userBean = new UserBean();
        try {
        	userBean.setUserId(rset.getString("user_id"));
        	userBean.setPassword(rset.getString("password"));
        	userBean.setUserName(rset.getString("user_name"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            userBean = null;
        }
        return userBean;
    }

}
