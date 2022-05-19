package com.alphacmc.alphasweb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alphacmc.alphasweb.bean.CustomerBean;
import com.alphacmc.alphasweb.bean.OrderBaseBean;
import com.alphacmc.alphasweb.bean.ProdBean;
import com.alphacmc.alphasweb.dao.CustomerDao;
import com.alphacmc.alphasweb.dao.OrderBaseDao;
import com.alphacmc.alphasweb.dao.ProdDao;
import com.alphacmc.alphasweb.form.OrderForm;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    // DAO
    private OrderBaseDao orderDao = null;
    private CustomerDao customerDao = null;
    private ProdDao prodDao = null;

    public OrderServlet() {
        orderDao = new OrderBaseDao();
        customerDao = new CustomerDao();
        prodDao = new ProdDao();
    }

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // パラメータ取得
        final String orderId = request.getParameter("orderId");

        // 顧客リスト
        List<CustomerBean> customerList = customerDao.getResultList("SELECT customer_id, customer_name FROM customer ORDER BY customer_id");
        // 商品リスト
        List<ProdBean> prodList = prodDao.getResultList("SELECT prod_id, prod_name, price FROM prod ORDER BY prod_id");
        
        final String forwardJSP;
        // 顧客情報画面フォームBeanの準備
        final OrderForm orderForm = new OrderForm();
        if (orderId == null || "".equals(orderId)) {
            // 新規
            forwardJSP = "orderNew.jsp";
        } else {
            String sql = "SELECT order_id, order_date, customer_id, prod_id, qty FROM orders WHERE order_id = " + orderId;
            
            OrderBaseBean order = orderDao.getResult(sql);
            orderForm.setOrderId(String.valueOf(order.getOrderId()));
            orderForm.setOrderDate(order.getOrderDate());
            orderForm.setCustomerId(String.valueOf(order.getCustomerId()));
            orderForm.setProdId(String.valueOf(order.getProdId()));
            orderForm.setQty(String.valueOf(order.getQty()));
            forwardJSP = "order.jsp";
        }
        // リクエストコンテキスト設定
        request.setAttribute("orderForm", orderForm);
        request.setAttribute("customerList", customerList);
        request.setAttribute("prodList", prodList);
        
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
