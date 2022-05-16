package com.alphacmc.alphasweb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.alphacmc.alphasweb.bean.ProdBean;

/**
 *
 * @author yohira
 *
 */
public class ProdListServlet extends HttpServlet {

    private static final String JDBC_JNDI = "java:comp/env/jdbc/postgres";

    // データソース
    private DataSource dataSource = null;

    public ProdListServlet() {
    }

    /**
     *
     */
    @Override
    public void init() throws ServletException {

        try {
            Context context=new InitialContext();
            dataSource=(DataSource)context.lookup(JDBC_JNDI);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // パラメータ取得
//        Map<String, String[]> paramMap = request.getParameterMap();

        // Customer保存用List
        List<ProdBean> prodList  = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            //PostgreSQLへ接続
            conn = dataSource.getConnection();

            //SELECT文の準備
            String sql = "SELECT prod_id, prod_name, price FROM prod";
            stmt = conn.createStatement();

            //SELECT文の実行
            rset = stmt.executeQuery(sql);

            //SELECT結果の受け取り
            while(rset.next()){

                // 顧客情報をリストに追加
                ProdBean prod = new ProdBean();
                prod.setProdId(rset.getInt("prod_id"));
                prod.setProdName(rset.getString("prod_name"));
                prod.setPrice(rset.getInt("price"));

                prodList.add(prod);
            }

        } catch (SQLException e){
                e.printStackTrace();
        }
        finally {
            try {
                if(rset != null) rset.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        // リクエストコンテキスト設定
        request.setAttribute("prodList", prodList);

        System.out.println("prod size=" + prodList.size());

        // 画面遷移
        RequestDispatcher dispatch = request.getRequestDispatcher("/prodList.jsp");
        dispatch.forward(request, response);

    }

    /**
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
