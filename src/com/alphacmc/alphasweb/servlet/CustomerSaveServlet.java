package com.alphacmc.alphasweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alphacmc.alphasweb.bean.CustomerBean;
import com.alphacmc.alphasweb.dao.CustomerDao;
import com.alphacmc.alphasweb.form.CustomerForm;

@WebServlet("/customerSave")
public class CustomerSaveServlet extends HttpServlet {

    // DAO
    private CustomerDao customerDao = null;

    public CustomerSaveServlet() {
        customerDao = new CustomerDao();
    }

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // パラメータ取得 & Formセット
    	CustomerForm customerForm = new CustomerForm();
    	customerForm.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
    	customerForm.setCustomerName(request.getParameter("customerName"));

    	System.out.println(customerForm.getCustomerId());
    	System.out.println(customerForm.getCustomerName());

    	// 読み込む
        final String qureySQL = "SELECT customer_id, customer_name FROM customer WHERE customer_id = " + customerForm.getCustomerId();
    	CustomerBean customer = customerDao.getResult(qureySQL);

    	final String updateSQL;
    	if (customer == null) {
    		updateSQL = "INSERT INTO customer(customer_id, customer_name) VALUES(" + customerForm.getCustomerId() + ", '" +  customerForm.getCustomerName() + "')";
    	} else {
    		updateSQL = "UPDATE customer SET customer_name = '" + customerForm.getCustomerName() + "' WHERE customer_id = " +  customerForm.getCustomerId();
    	}
    	customerDao.executeSQL(updateSQL);

        // 画面遷移
        response.sendRedirect("/alphasweb/customerList");
    }

    /**
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
