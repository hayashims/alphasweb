package com.alphacmc.alphasweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alphacmc.alphasweb.bean.OrderBaseBean;
import com.alphacmc.alphasweb.dao.OrderBaseDao;
import com.alphacmc.alphasweb.form.OrderForm;

@WebServlet("/orderSave")
public class OrderSaveServlet extends HttpServlet {

    // DAO
    private OrderBaseDao orderDao = null;

    public OrderSaveServlet() {
    	orderDao = new OrderBaseDao();
    }

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // パラメータ取得 & Formセット
    	OrderForm orderForm = new OrderForm();
    	orderForm.setOrderId(request.getParameter("orderId"));
    	orderForm.setCustomerId(request.getParameter("customerId"));
    	orderForm.setProdId(request.getParameter("prodId"));
    	orderForm.setQty(request.getParameter("qty"));
    	
    	// qty の数字チェック
    	if (!isNumeric(orderForm.getQty())) {
    		// qty が数字ではない場合
            // リクエストコンテキスト設定
            request.setAttribute("message", "数量は数値をセットしてください。");
            request.setAttribute("orderForm", orderForm);
            // 画面遷移
            RequestDispatcher dispatch = request.getRequestDispatcher("/customerNew.jsp");
            dispatch.forward(request, response);
            return;
    	}
    	
    	System.out.println(orderForm.getOrderId());
    	System.out.println(orderForm.getCustomerId());
    	System.out.println(orderForm.getProdId());

    	// 読み込む
        final String qureySQL = "SELECT order_id, order_date, customer_id, prod_id, qty FROM orders WHERE order_id = " + orderForm.getOrderId();
    	OrderBaseBean customer = orderDao.getResult(qureySQL);

    	final String updateSQL;
    	if (customer == null) {
    		updateSQL = "INSERT INTO orders(order_id, order_date, customer_id, prod_id, qty) VALUES(" + orderForm.getOrderId() + ", current_timestamp, " + orderForm.getCustomerId() 
    			+ ", " + orderForm.getProdId() + ", " + orderForm.getQty() + ")";
    	} else {
    		updateSQL = "UPDATE orders SET customer_id = " + orderForm.getCustomerId() + ", prod_id = " + orderForm.getProdId() + ", qty = " + orderForm.getQty() + " WHERE order_id = " +  orderForm.getOrderId();
    	}
    	orderDao.executeSQL(updateSQL);

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

    /**
     * 数値チェック
     * @param param
     * @return
     */
    private boolean isNumeric(String param) {
    	try {
    		Integer.parseInt(param);
    	} catch (Exception ex) {
    		// ProdIdが数字ではない場合
    		return false;
    	}
    	return true;
    }   
    
}
