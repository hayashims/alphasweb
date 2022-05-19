package com.alphacmc.alphasweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alphacmc.alphasweb.dao.OrderDao;

@WebServlet("/orderListop")
public class OrderListOpServlet  extends HttpServlet {

    // DAO
    private OrderDao orderDao = null;


    public OrderListOpServlet() {
    	orderDao = new OrderDao();
    }

    /**
    *
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // パラメータ取得
       final String[] deleleMarks = request.getParameterValues("check");

       final String deleteSqlPart = "DELETE FROM orders WHERE order_id = ";
       // 削除設定されたレコードを削除
       for (String deleleMark : deleleMarks) {
    	   // delete SQL
    	   String deleteSql = deleteSqlPart + deleleMark;
           System.out.println("SQL=" + deleteSql);
           orderDao.executeSQL(deleteSql);
       }

       // 画面遷移
       response.sendRedirect("/alphasweb/orderList");

   }

   /**
    *
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
   }


}
