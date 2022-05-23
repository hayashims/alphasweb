package com.alphacmc.alphasweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alphacmc.alphasweb.bean.UserBean;
import com.alphacmc.alphasweb.dao.UserDao;
import com.alphacmc.alphasweb.form.UserForm;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // DAO
    private UserDao userDao = null;

    public LoginServlet() {
    	userDao = new UserDao();
    }

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // パラメータ取得
    	UserForm userForm = new UserForm();
    	userForm.setUserId(request.getParameter("userId"));
    	userForm.setPassword(request.getParameter("password"));

        String sql = "SELECT user_id, password, user_name FROM users WHERE user_id = '" + userForm.getUserId() + "'";
        UserBean userBean = userDao.getResult(sql);	
        // 	ユーザIDが存在しないか、パスワードが不一致の場合
        if (userBean == null || !userBean.getPassword().equals(userForm.getPassword())) {
        	System.out.println("エラー");
            // リクエストコンテキスト設定
            request.setAttribute("message", "ユーザIDまたはパスワードに誤りがあります。");
            request.setAttribute("userForm", userForm);
            // 画面遷移
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
        	System.out.println("正常");
        	HttpSession session = request.getSession();
        	session.setAttribute("userBean", userBean);
            // 画面遷移
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    /**
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
