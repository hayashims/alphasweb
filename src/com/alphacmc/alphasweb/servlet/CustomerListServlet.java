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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.alphacmc.alphasweb.bean.CustomerBean;

/**
 *
 * @author yohira
 *
 */
@WebServlet("/customerList")
public class CustomerListServlet extends HttpServlet {

    // JNDIプレフィックス
    private static final String JDBC_JNDI = "java:comp/env/jdbc/postgres";

    // データソース
    private DataSource dataSource = null;

    public CustomerListServlet() {
    }

    /**
     *
     */
    @Override
    public void init() throws ServletException {

        try {
            Context context = new InitialContext();
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
        List<CustomerBean> customerList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            //PostgreSQLへ接続
            conn = dataSource.getConnection();

            //SELECT文の準備
            String sql = "SELECT customer_id, customer_name FROM customer ORDER BY customer_id";
            stmt = conn.createStatement();

            //SELECT文の実行
            rset = stmt.executeQuery(sql);

            //SELECT結果の受け取り
            while(rset.next()){

//              int customerId = rset.getInt("customer_id");
//              String customerName = rset.getString("customer_name");
//              System.out.println(customerId + "," + customerName);

                // 顧客情報をリストに追加
                CustomerBean customer = new CustomerBean();
                customer.setCustomerId(rset.getInt("customer_id"));
                customer.setCustomerName(rset.getString("customer_name"));
                customerList.add(customer);
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
        request.setAttribute("customerList", customerList);

        // 画面遷移
        RequestDispatcher dispatch = request.getRequestDispatcher("/customerList.jsp");
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
