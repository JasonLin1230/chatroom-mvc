/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JasonLin
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userBean.User;
import service.UserException;
import service.UserService;

public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");         //设置编码
        String requrl=request.getHeader("Referer");//判断请求来源
        HttpSession session=request.getSession(true);
        String username=request.getParameter("username");
        String userpass=request.getParameter("password");
        User user=new User();
        user.setUsername(username);
        user.setPassword(userpass);
        UserService service=new UserService();
        try{
            service.register(user);
            if((requrl.indexOf("login.jsp"))!=-1){
                session.setAttribute("login_feedback","注册成功，可以登录啦！");
                response.sendRedirect("login.jsp");
            }else{
                session.setAttribute("manage_op_feedback","操作成功！");
                request.getRequestDispatcher("Manage").forward(request, response);
            }
        }catch(UserException e){
            if((requrl.indexOf("login.jsp"))!=-1){
                session.setAttribute("login_feedback",e.getMessage());
                response.sendRedirect("login.jsp");
            }else{
                session.setAttribute("manage_op_feedback",e.getMessage());
                request.getRequestDispatcher("Manage").forward(request, response);
            }
        }
    }
}