/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import userBean.User;
import java.sql.*;
import java.util.*; 
/**
 *
 * @author JasonLin
 */
public class UserDao {
    public Connection getconnection() {
        String DRIVER="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/javaee";
        String username = "root";
        String password = "971230";
        Connection conn = null;
        // 加载驱动程序以连接数据库
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(url,username, password );
            System.out.println("======>数据库连接成功");
            return conn;
        }
        //捕获加载驱动程序异常
        catch ( ClassNotFoundException cnfex ) {
            System.err.println("装载 JDBC/ODBC 驱动程序失败。" );
            return null;
        }
        //捕获连接数据库异常
        catch ( SQLException sqlex ) {
            System.err.println( "无法连接数据库" );
            return null;
        }
    }
    //释放MySQL连接
    public void deconnSQL(Connection conn) {
        try {
            if (conn != null){
                conn.close();
                System.out.println("======>数据库断开成功");
            }
        } catch (SQLException e) {
            System.out.println("关闭数据库问题 ：");
        }
    }
    public User login(User user)  //用户登录
    {
        UserDao dao=new UserDao();
        Connection conn=dao.getconnection();
        PreparedStatement preparedStmt=null;
        ResultSet sqlRst=null;
        String username=user.getUsername();
        String useridentity=user.getIdentity();
        User result=new User();
        try{
            preparedStmt=conn.prepareStatement("select password from login where username=? and identity=?");
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, useridentity);
            sqlRst=preparedStmt.executeQuery();
            if(sqlRst.next()){
                result.setPassword(sqlRst.getString("password"));
            }else{
                result=null;
            }
        }catch(java.sql.SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                if(sqlRst!=null){
                    sqlRst.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    };
    public int newUser(User user)//用户注册、管理员录入用户
    {
        UserDao dao=new UserDao();
        Connection conn=dao.getconnection();
        PreparedStatement preparedStmt=null;
        ResultSet sqlRst=null;
        String username=user.getUsername();
        String userpass=user.getPassword();
        try{
            preparedStmt=conn.prepareStatement("insert into login(username,password)value(?,?)");
            preparedStmt.setString(1,username);
            preparedStmt.setString(2,userpass);
            return preparedStmt.executeUpdate();
        }catch(java.sql.SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                if(sqlRst!=null){
                    sqlRst.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }
    public int dispose(User user)//删除或注销用户
    {
        UserDao dao=new UserDao();
        Connection conn=dao.getconnection();
        PreparedStatement preparedStmt=null;
        ResultSet sqlRst=null;
        String username=user.getUsername();
        try{
            preparedStmt=conn.prepareStatement("delete from login where username=?");
            preparedStmt.setString(1,username);
            return preparedStmt.executeUpdate();
        }catch(java.sql.SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                if(sqlRst!=null){
                    sqlRst.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }
    public static List<User> queryAll()//查询表中所有的用户
    {
        UserDao dao=new UserDao();
        Connection conn=dao.getconnection();
        PreparedStatement preparedStmt=null;
        ResultSet sqlRst=null;
        List<User> results=new ArrayList<>();
        try{
            preparedStmt=conn.prepareStatement("select id,username,password from login where identity='staff'");
            sqlRst=preparedStmt.executeQuery();
            while(sqlRst.next()){
                User tempUser=new User();
                tempUser.setId(sqlRst.getString("id"));
                tempUser.setUsername(sqlRst.getString("username"));
                tempUser.setPassword(sqlRst.getString("password"));
                results.add(tempUser);
            }
        }catch(java.sql.SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                if(sqlRst!=null){
                    sqlRst.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return results;
    }
    public static List<User> query(User user)//查询满足条件的所有用户
    {
        UserDao dao=new UserDao();
        Connection conn=dao.getconnection();
        Statement stat =null;
        ResultSet sqlRst=null;
        List<User> results=new ArrayList<>();
        String userid=user.getId();
        String username=user.getUsername();
        String userpass=user.getPassword();
        try{
            StringBuilder sql=new StringBuilder("select * from login where 1=1");
            if(!userid.isEmpty()){
                    sql.append(" and id like '%"+userid+"%' ");
            }
            if(!username.isEmpty()){
                    sql.append(" and username like '%"+username+"%' ");
            }
            if(!userpass.isEmpty()){
                    sql.append(" and password like '%"+userpass+"%' ");
            }
            sql.append(";");
            stat=conn.createStatement();
            sqlRst=stat.executeQuery(sql.toString());
            while(sqlRst.next()){
                User tempUser=new User();
                tempUser.setId(sqlRst.getString("id"));
                tempUser.setUsername(sqlRst.getString("username"));
                tempUser.setPassword(sqlRst.getString("password"));
                results.add(tempUser);
            }
        }catch(java.sql.SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                if(sqlRst!=null){
                    sqlRst.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return results;
    }
    public int updatePass(User user)//用户修改密码
    {
        UserDao dao=new UserDao();
        Connection conn=dao.getconnection();
        PreparedStatement preparedStmt=null;
        ResultSet sqlRst=null;
        String old_pass=user.getOldPassword();
        String username=user.getUsername();
        String userpass=user.getPassword();
        try{
            preparedStmt=conn.prepareStatement("update login set password=? where username=? and password=?");
            preparedStmt.setString(1,userpass);
            preparedStmt.setString(2,username);
            preparedStmt.setString(3,old_pass);
            return preparedStmt.executeUpdate();
        }catch(java.sql.SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                if(sqlRst!=null){
                    sqlRst.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }
    public int updateInfo(User user)//管理员修改用户信息
    {
        UserDao dao=new UserDao();
        Connection conn=dao.getconnection();
        PreparedStatement preparedStmt=null;
        ResultSet sqlRst=null;
        String userid=user.getId();
        String username=user.getUsername();
        String userpass=user.getPassword();
        try{
            preparedStmt=conn.prepareStatement("update login set username=?,password=? where id=?");
            preparedStmt.setString(1,username);
            preparedStmt.setString(2,userpass);
            preparedStmt.setString(3,userid);
            return preparedStmt.executeUpdate();
        }catch(java.sql.SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                if(sqlRst!=null){
                    sqlRst.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }
}
