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

import com.alphacmc.alphasweb.bean.OrderBean;

/**
 *
 * @author yohira
 *
 */
@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {

    private static final String JDBC_JNDI = "java:comp/env/jdbc/postgres";

    // データソース
    private DataSource dataSource = null;

    public OrderListServlet() {
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
        List<OrderBean> orderList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            //PostgreSQLへ接続
            conn = dataSource.getConnection();

            //SELECT文の準備
            String sql = "SELECT order_id, order_date, c.customer_name, p.prod_name, p.price, qty, (qty * p.price) AS amount "
                       + "FROM orders o "
                       + "LEFT OUTER JOIN customer c "
                       + "ON o.customer_id = c.customer_id "
                       + "LEFT OUTER JOIN prod p "
                       + "ON o.prod_id = p.prod_id "
                       + "ORDER BY order_id";

            stmt = conn.createStatement();

            //SELECT文の実行
            rset = stmt.executeQuery(sql);

            //SELECT結果の受け取り
            while(rset.next()){

                // 注文データをリストに追加
                OrderBean order = new OrderBean();
                order.setOrderId(rset.getInt("order_id"));
                order.setOrderDate(rset.getTimestamp("order_date"));
                order.setCustomerName(rset.getString("customer_name"));
                order.setProdName(rset.getString("prod_name"));
                order.setPrice(rset.getInt("price"));
                order.setQty(rset.getInt("qty"));
                order.setAmount(rset.getInt("amount"));
                orderList.add(order);
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
        request.setAttribute("orderList", orderList);

        // 画面遷移
        RequestDispatcher dispatch = request.getRequestDispatcher("/orderList.jsp");
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
