/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Chat extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");         //设置编码
        HttpSession session=request.getSession(true);
        String username=request.getParameter("username");
        String my_meg=request.getParameter("my_meg");
        my_meg=username+" ： "+my_meg;
        ArrayList chat_records=(ArrayList)getServletContext().getAttribute("chat_records");
        if(chat_records==null){
            chat_records=new ArrayList();
        }
        chat_records.add(my_meg);
        getServletContext().setAttribute("chat_records",chat_records);
        session.setAttribute("cur_name",username);
        response.sendRedirect("ChatRoom.jsp");
    }
}