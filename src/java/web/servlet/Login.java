/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

/*
 * @author JasonLin
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserException;
import userBean.User;
import service.UserService;

public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");         //设置编码
        HttpSession session=request.getSession(true);
        String username=request.getParameter("username");
        String userpass=request.getParameter("password");
        String useridentity=request.getParameter("identity");
        User user=new User();
        user.setUsername(username);
        user.setPassword(userpass);
        user.setIdentity(useridentity);
        UserService service=new UserService();
        try{
            service.login(user);
            if(useridentity.equals("admin")){
                request.getRequestDispatcher("Manage").forward(request, response);
            }else{
                session.setAttribute("cur_name",username);
                response.sendRedirect("ChatRoom.jsp");
            }
        }catch(UserException e){
            session.setAttribute("login_feedback",e.getMessage());
            response.sendRedirect("login.jsp");
        }
    }
}