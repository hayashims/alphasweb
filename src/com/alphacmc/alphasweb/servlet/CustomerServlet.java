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
import com.alphacmc.alphasweb.form.CustomerForm;

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
        final String customerId = request.getParameter("customerId");

        final String forwardJSP;

        // 顧客情報画面フォームBeanの準備
        final CustomerForm customerForm = new CustomerForm();;
        if (customerId == null || "".equals(customerId)) {
            // 新規
            forwardJSP = "customerNew.jsp";
        } else {
            String sql = "SELECT customer_id, customer_name FROM customer WHERE customer_id = " + customerId;
            CustomerBean customer = customerDao.getResult(sql);
            customerForm.setCustomerId(String.valueOf(customer.getCustomerId()));
            customerForm.setCustomerName(customer.getCustomerName());
            forwardJSP = "customer.jsp";
        }
        // リクエストコンテキスト設定
        request.setAttribute("customerForm", customerForm);

        // 画面遷移
        RequestDispatcher dispatch = request.getRequestDispatcher("/" + forwardJSP);
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
