<%-- 
    Document   : query
    Created on : 2017-12-20, 12:57:28
    Author     : JasonLin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@page import="java.util.*"%>
<%@page import="userBean.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>聊天室后台管理系统</title>
        <link rel="stylesheet" href="./css/modal.css"/>
        <link rel="stylesheet" href="./css/manage.css"/>
    </head>
    <body>
        <h1>用户信息表</h1>
        <div class="content">
            <div class="top_option">
                <a href="manage.jsp" style="margin-right: 3px;">首页</a>
                <a class="md-trigger"  data-modal="modal-1" option="new" style="margin-right: 3px;">录入</a>
                <a class="md-trigger"  data-modal="modal-1" option="query">查询</a>
            </div>
            <table>
                <tr>
                    <th width="150px;">ID</th>
                    <th width="350px;">用户名</th>
                    <th width="350px;">密码</th>
                    <th width="110px;">操作</th>
                </tr>
        <%
            request.setCharacterEncoding("UTF-8");
            String manage_op_feedback=(String)session.getAttribute("manage_op_feedback");
            List<User> results=(List)session.getAttribute("querylist");
            for(User tempUser:results){
        %>
                <tr>
                    <td><%=tempUser.getId()%></td>
                    <td><%=tempUser.getUsername()%></td>
                    <td><%=tempUser.getPassword()%></td>
                    <td>
                        <a href="#" class="md-trigger" option="modify" data-modal="modal-1" user-id="<%=tempUser.getId()%>" user-name="<%=tempUser.getUsername()%>" user-pass="<%=tempUser.getPassword()%>">修改</a>
                        <form action="Dispose" method="post" style="display: inline-block;">
                            <input type="text" name="username" style="display: none;" value="<%=tempUser.getUsername()%>">
                            <button type="submit" class="op_btn" onclick="return confirm('您确认要执行删除吗?')">删除</button>
                        </form>
                    </td>
                </tr>
            <%}%>
            </table>
        </div>
    <div class="md-modal md-effect-1" id="modal-1"><!--弹出框模板-->
        <div class="md-content">
            <h3>注册新用户</h3>
            <form action="NewUser.jsp" method="post">
                <ul>
                    <li class="input-wrap" style="display: none;" id="userid_wrap">
                        <input type="text"  placeholder="ID" name="userid" id="userid">
                    </li>
                    <li class="input-wrap">
                        <input type="text" placeholder="用户名" name="username" id="username">
                    </li>
                    <li class="input-wrap">
                        <input type="text" placeholder="密码" name="password" id="userpass">
                    </li>
                </ul>
                <div class="exp-btn-group">
                    <button type="submit" class="md-sub">确定</button>
                </div>
            </form>
        </div>
    </div>
    <div class="md-overlay"></div>
    <div id="manage_op_feedback" style="visibility: hidden;"><%=manage_op_feedback%></div>
    <canvas id="Mycanvas" style="z-index: -1;position:absolute;top: 0;width: 100%;height: 100%;"></canvas>
    <script src="./js/jquery.min.js"></script>
    <script src="./js/canvas.js"></script>
    <script src="./js/layer.js"></script>
    <script src="./js/manage.js"></script>
    </body>
</html>