# ChatRoom

*基于MVC开发的简易聊天室*


>JavaEE编程技术

## 功能描述

* 普通用户可通过注册获取用户名和密码

* 普通用户通过用户名和密码登录进入聊天室

* 普通用户可以修改自己密码，也可以注销当前用户

* 聊天室可显示以往聊天记录，可发言，刷新聊天记录

* 登录页面，用户名输入框显示上一次成功登录的用户名，快捷登录

* 主聊天室页面 可显示 当前在线人数 及 在线用户名称

* 管理员登录，拥有对所有的用户信息的删除、修改、多条件查询，并可以录入新用户

* 简单的三层架构

## 目录结构

初始的目录结构如下：

~~~
├WEB页目录
│ ├─css                     css目录
│ │  ├─chatroom.css         主聊天页面样式
│ │  ├─login.css            登录页面样式
│ │  ├─manage.css           管理界面样式
│ │  └─modal.css            弹出框样式
│ │
│ ├─js                      js目录
│ │  ├─theme                主题目录
│ │  │  └─default           默认内容目录
│ │  │     ├─head_1.png     默认头像1
│ │  │     ├─head_2.png     默认头像2
│ │  │     ├─head_3.png     默认头像3
│ │  │     ├─head_4.png     默认头像4
│ │  │     ├─head_5.png     默认头像5
│ │  │     ├─icon-ext.png   箭头图标
│ │  │     ├─icon.png       图标文件
│ │  │     ├─layer.css      layer样式
│ │  │     ├─loading-0.gif  加载图标1
│ │  │     ├─loading-1.gif  加载图标2
│ │  │     └─loading-2.gif  加载图标3
│ │  │
│ │  ├─canvas.js            背景canvas动画
│ │  ├─chatroom.js          主聊天是页面js文件
│ │  ├─jquery.min.js        jquery文件
│ │  ├─layer.js             layer文件
│ │  ├─login.js             登录页面js文件
│ │  └─manage.js            管理页面文件
│ │
│ ├─ChatRoom.jsp            主聊天室的jsp文件
│ ├─error.jsp               发生错误时提示的jsp文件
│ ├─index.jsp               默认入口文件
│ ├─login.jsp               登录页面jsp文件
│ ├─manage.jsp              管理员页面jsp文件
│ └─result.jsp              显示管理员查询结果的jsp文件
│
└源包目录
  ├─dao
  │  └─UserDao.java         操作数据库的文件
  │ 
  ├─service
  │  ├─UserException.java   service层抛出异常文件
  │  └─UserService.java     处理具体业务的文件
  │ 
  ├─userBean
  │  └─User.java            封装领域对象的文件
  │ 
  └─web.servlet
     ├─ChangeInfo.java      管理员修改用户信息的文件
     ├─ChangePass.java      用户修改密码的文件
     ├─Chat.java            处理聊天消息的文件
     ├─Dispose.java         销毁及删除用户的文件
     ├─Login.java           检测登录的文件
     ├─Manage.java          查询所有用户的文件
     ├─Out.java             用户退出聊天室的文件
     ├─Query.java           管理员查询用户的文件
     └─Register.java        用户注册的文件
~~~

## 项目截图

* 登录界面

![login](https://github.com/JasonLin1230/chatroom-mvc/blob/master/pics/Image%20001.png?raw=true)

* 聊天室界面

![login](https://github.com/JasonLin1230/chatroom-mvc/blob/master/pics/Image%20002.png?raw=true)

* 用户修改密码

![login](https://github.com/JasonLin1230/chatroom-mvc/blob/master/pics/Image%20003.png?raw=true)

* 后台管理界面

![login](https://github.com/JasonLin1230/chatroom-mvc/blob/master/pics/Image%20004.png?raw=true)

* 管理员修改用户信息

![login](https://github.com/JasonLin1230/chatroom-mvc/blob/master/pics/Image%20005.png?raw=true)

* 管理员查询用户

![login](https://github.com/JasonLin1230/chatroom-mvc/blob/master/pics/Image%20006.png?raw=true)
