<%-- 
    Document   : ChatRoom
    Created on : 2017-12-10, 14:47:59
    Author     : JasonLin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="pragma" content="no-cache">
        <title>聊天室</title>
        <link rel="stylesheet" href="./css/modal.css"/>
        <link rel="stylesheet" href="./css/chatroom.css"/>
    </head>
    <body>
        <div class="content">
            <p class="subtitle pulse animated">聊天室</p>
            <%
            request.setCharacterEncoding("UTF-8");
            String Name=new String();
            Name=(String)session.getAttribute("cur_name");
            boolean hasLog=false;
            ArrayList names=(ArrayList)session.getAttribute("lognames");
            if(names==null){
                names=new ArrayList();
                names.add(Name);
                session.setAttribute("lognames",names);
            }else{
                for(int i=0;i<names.size();i++){
                    String temp=(String)names.get(i);
                    if(temp.equals(Name)){
                        hasLog=true;
                        break;
                    }
                }
                if(!hasLog){
                    names.add(Name);
                    session.setAttribute("lognames",names);
                }
            }
            String change_feedback=(String)session.getAttribute("change_feedback");
            %>
            <div id="none3" class="none"><%=change_feedback%></div>
            <div class="main">
                <div class="room">
                    <p class="cur_user">欢迎您，<%=Name%></p>
                    <div class="record">
                        <% ArrayList chat_records=new ArrayList(); 
                        chat_records=(ArrayList)application.getAttribute("chat_records");
                        if(chat_records!=null){
                            for (int i=0;i<chat_records.size();i++)
                            {
                                String temp_record=(String)chat_records.get(i);
                                out.println(temp_record);
                                out.println("<br/>");
                            }
                        }
                        %>
                    </div>
                    <form action="Chat" method="post">
                        <input type="text" style="display: none;" value="<%=Name%>" name="username">
                        <textarea rows="3" autofocus="autofocus" name="my_meg" required="required"></textarea>
                        <div style="overflow: hidden;">
                            <button type="submit" id="send_meg">发送</button>
                            <button type="reset">重置</button>
                        </div>
                    </form>
                </div>
                <div class="member">
                    <h4 style="font-size:15px;font-weight:400;">当前在线人数：<%=names.size()%></h4>
                    <ul>
                    <%for(int i=0;i<names.size();i++){
                        String temp=(String)names.get(i);
                        out.println("<li class='member_li'>"+temp+"</li>");
                    }
                    %>
                    </ul>
                </div>
                <div class="change_btn">
                    <a type="submit" class="md-trigger" data-modal="modal-1">修改密码</a>
                </div>
                <form action="Out" method="post" id="out_form" class="top_form">
                    <input type="text" style="display: none;" value="<%=Name%>" name="username">
                    <button type="submit" id="change">退出</button>
                </form>
                <form action="Dispose" method="post" id="dispose_form" class="top_form">
                    <input type="text" style="display: none;" value="<%=Name%>" name="username">
                    <button type="submit" id="out">退出并销毁</button>
                </form>
            </div>
            <p class="copy fadeIn animated">Copyright © 2017.JasonLin</p>
        </div>
        <div class="md-modal md-effect-1" id="modal-1">
            <div class="md-content">
                <h3>修改密码</h3>
                <form action="ChangePass" method="post">
                    <ul>
                        <li class="input-wrap" style="display: none;">
                            <input type="text" name="username" value="<%=Name%>">
                        </li>
                        <li class="input-wrap">
                            <input required type="text" placeholder="原始密码" name="old_password">
                        </li>
                        <li class="input-wrap">
                            <input required type="password" placeholder="新密码" name="new_password">
                        </li>
                    </ul>
                    <div class="exp-btn-group">
                        <button type="submit" class="md-sub">确定</button>
                    </div>
                </form>
            </div>
	</div>
        <div class="md-overlay"></div>
    <canvas id="Mycanvas" style="z-index: -1;position:absolute;top: 0;width: 100%;height: 100%;"></canvas>
    <script src="./js/jquery.min.js"></script>
    <script src="./js/canvas.js"></script>
    <script src="./js/layer.js"></script>
    <script src="./js/chatroom.js"></script>
    </body>
</html>
