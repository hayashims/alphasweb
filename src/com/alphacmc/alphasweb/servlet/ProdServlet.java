package com.alphacmc.alphasweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alphacmc.alphasweb.bean.ProdBean;
import com.alphacmc.alphasweb.dao.ProdDao;
import com.alphacmc.alphasweb.form.ProdForm;

@WebServlet("/prod")
public class ProdServlet extends HttpServlet {

    // DAO
    private ProdDao prodDao = null;

    public ProdServlet() {
    	prodDao = new ProdDao();
    }

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // パラメータ取得
        final String prodId = request.getParameter("prodId");

        final String forwardJSP;

        // 商品情報画面フォームBeanの準備
        final ProdForm prodForm = new ProdForm();
        if (prodId == null || "".equals(prodId)) {
            // 新規
            forwardJSP = "prodNew.jsp";
        } else {
            String sql = "SELECT prod_id, prod_name, price FROM prod WHERE prod_id = " + prodId;
            ProdBean prod = prodDao.getResult(sql);
            prodForm.setProdId(String.valueOf(prod.getProdId()));
            prodForm.setProdName(prod.getProdName());
            prodForm.setPrice(String.valueOf(prod.getPrice()));
            forwardJSP = "prod.jsp";
        }
        // リクエストコンテキスト設定
        request.setAttribute("prodForm", prodForm);

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
