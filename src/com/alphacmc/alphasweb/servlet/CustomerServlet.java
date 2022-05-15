package com.alphacmc.alphasweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alphacmc.alphasweb.bean.CustomerBean;
import com.alphacmc.alphasweb.dao.CustomerDao;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    // DAO
    private CustomerDao customerDao = null;

    public CustomerServlet() {
        customerDao = new CustomerDao();
    }

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // パラメータ取得
        String customerId = request.getParameter("customerId");

        final CustomerBean customer;
        if (customerId == null) {
            // 新規
            customer = new CustomerBean();
        } else {
            String sql = "SELECT customer_id, customer_name FROM customer WHERE customer_id = " + customerId;
            customer = customerDao.getResult(sql);
        }
        // リクエストコンテキスト設定
        request.setAttribute("customer", customer);

        // 画面遷移
        RequestDispatcher dispatch = request.getRequestDispatcher("/customer.jsp");
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
