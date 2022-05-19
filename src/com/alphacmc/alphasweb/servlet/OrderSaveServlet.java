package com.alphacmc.alphasweb.servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/orderSave")
public class OrderSaveServlet extends HttpServlet {

    // DAO
    private OrderBaseDao orderDao = null;
    private CustomerDao customerDao = null;
    private ProdDao prodDao = null;

    public OrderSaveServlet() {
    	orderDao = new OrderBaseDao();
        customerDao = new CustomerDao();
        prodDao = new ProdDao();
	}

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// パラメータ取得 & Formセット
    	// 処理タイプ
    	String procType = request.getParameter("procType");  
    	
    	OrderForm orderForm = new OrderForm();
    	orderForm.setOrderId(request.getParameter("orderId"));
    	orderForm.setCustomerId(request.getParameter("customerId"));
    	orderForm.setProdId(request.getParameter("prodId"));
    	orderForm.setQty(request.getParameter("qty"));

    	// orderId の数字チェック
    	String message = "";
    	if (!isNumeric(orderForm.getOrderId())) {
    		message += "注文IDは数値をセットしてください。 ";
    	}
    	// qty の数字チェック
    	if (!isNumeric(orderForm.getQty())) {
    		message += "数量は数値をセットしてください。";
    	}
    	// エラー発生時
    	if (!"".equals(message)) {
    		request.setAttribute("message", message);
            request.setAttribute("orderForm", orderForm);

            // 顧客リスト
            List<CustomerBean> customerList = customerDao.getResultList("SELECT customer_id, customer_name FROM customer ORDER BY customer_id");
            request.setAttribute("customerList", customerList);
            // 商品リスト
            List<ProdBean> prodList = prodDao.getResultList("SELECT prod_id, prod_name, price FROM prod ORDER BY prod_id");
            request.setAttribute("prodList", prodList);
            
            if (procType == null || "".equals(procType)) {

            	System.out.println(orderForm.getCustomerId());
            	System.out.println(orderForm.getProdId());

                request.getRequestDispatcher("/order.jsp").forward(request, response);
            } else {

            	System.out.println(orderForm.getOrderId());
            	System.out.println(orderForm.getCustomerId());
            	System.out.println(orderForm.getProdId());

            	request.getRequestDispatcher("/orderNew.jsp").forward(request, response);
            }
            return;
    	}
    	
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
    	if (param.matches("[0-9]+")) {
    		return true; 
    	}
    	return false;
    }   
    
}
