package com.alphacmc.alphasweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 共通DAOクラス
 * @author yohira
 *
 * @param <T> Bean型
 */
public abstract class BaseDao<T> {

    // JNDI名
    private static final String JDBC_JNDI = "java:comp/env/jdbc/postgres";
    // データソース
    private DataSource dataSource = null;

    public BaseDao() {
        try {
            Context context=new InitialContext();
            dataSource=(DataSource)context.lookup(JDBC_JNDI);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> getResultList(String sql) {

        // 結果行の格納リスト
        List<T> list = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            //PostgreSQLへ接続
            conn = dataSource.getConnection();
            //SELECT文の準備
            stmt = conn.createStatement();
            //SELECT文の実行
            rset = stmt.executeQuery(sql);
            //SELECT結果の受け取り
            while(rset.next()){
                // 結果業をBeanに設定しリストに追加
                T t = setBean(rset);
                list.add(t);
            }
        } catch (Exception e){
                e.printStackTrace();
        } finally {
            try {
                if(rset != null) rset.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }

    public T getResult(String sql) {

        // 結果行の格納リスト
        T t = null;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            //PostgreSQLへ接続
            conn = dataSource.getConnection();
            //SELECT文の準備
            stmt = conn.createStatement();
            //SELECT文の実行
            rset = stmt.executeQuery(sql);
            //SELECT結果の受け取り
            if(rset.next()){
                // 結果業をBeanに設定しリストに追加
                t = setBean(rset);
            }
        } catch (Exception e){
                e.printStackTrace();
        } finally {
            try {
                if(rset != null) rset.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return t;
    }


    abstract protected T setBean(ResultSet rset);


}
