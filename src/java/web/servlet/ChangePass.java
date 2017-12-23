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
import service.UserException;
import service.UserService;

public class ChangePass extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");         //设置编码
        HttpSession session=request.getSession(true);
        String username=request.getParameter("username");
        String old_pass=request.getParameter("old_password");
        String userpass=request.getParameter("new_password");
        User user=new User();
        user.setUsername(username);
        user.setOldPassword(old_pass);
        user.setPassword(userpass);
        UserService service=new UserService();
        try{
            service.changepass(user);
            session.setAttribute("change_feedback","");
            session.setAttribute("login_feedback","修改成功，请重新登录！");
            response.sendRedirect("login.jsp");
        }catch(UserException e){
            session.setAttribute("change_feedback",e.getMessage());
            response.sendRedirect("ChatRoom.jsp");
        }
    }
}