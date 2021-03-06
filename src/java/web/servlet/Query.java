/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userBean.User;
import java.util.List;
import service.UserException;
import service.UserService;

public class Query extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");         //设置编码
        HttpSession session=request.getSession(true);
        String userid=request.getParameter("userid");
        String username=request.getParameter("username");
        String userpass=request.getParameter("password");
        User user=new User();
        user.setId(userid);
        user.setUsername(username);
        user.setPassword(userpass);
        UserService service=new UserService();
        try{
            List<User> results=service.query(user);
            session.setAttribute("querylist",results);
            response.sendRedirect("result.jsp");
        }catch(UserException e){
            session.setAttribute("manage_op_feedback",e.getMessage());
            response.sendRedirect("manage.jsp");
        }
    }
}