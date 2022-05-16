package com.alphacmc.alphasweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alphacmc.alphasweb.dao.ProdDao;

@WebServlet("/prodListop")
public class ProdListOpServlet  extends HttpServlet {

    // DAO
    private ProdDao prodDao = null;


    public ProdListOpServlet() {
    	prodDao = new ProdDao();
    }

    /**
    *
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // パラメータ取得
       final String[] deleleMarks = request.getParameterValues("check");

       final String deleteSqlPart = "DELETE FROM prod WHERE prod_id = ";
       // 削除設定されたレコードを削除
       for (String deleleMark : deleleMarks) {
    	   // delete SQL
    	   String deleteSql = deleteSqlPart + deleleMark;
           System.out.println("SQL=" + deleteSql);
    	   prodDao.executeSQL(deleteSql);
       }

       // 画面遷移
       response.sendRedirect("/alphasweb/prodList");

   }

   /**
    *
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
   }


}
