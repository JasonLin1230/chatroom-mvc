/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import userBean.User;
import dao.UserDao;
import java.util.List;

/**
 *
 * @author JasonLin
 */
public class UserService {
    UserDao dao=new UserDao();
    private HttpSession session; 
    private HttpServletRequest request;
    private HttpServletResponse response;
    public void login(User user) throws UserException{
        User result=dao.login(user);
        if(result==null){
            throw new UserException("当前用户名不存在");
        }else if(!user.getPassword().equals(result.getPassword())){
            throw new UserException("密码错误");
        }
    }
    public void register(User user) throws UserException{
        int result=dao.newUser(user);
        if(result==-1|| result==0){
            throw new UserException("当前用户名已存在");
        }
    }
    public void dispose(User user) throws UserException{
        int result=dao.dispose(user);
        if(result==-1|| result==0){
            throw new UserException("操作失败，请重试！");
        }
    }
    public List<User> queryAll() throws UserException{
        List<User> results=dao.queryAll();
        if(results==null){
            throw new UserException("操作失败，请重试！");
        }
        return results;
    }
    public List<User> query(User user) throws UserException{
        List<User> results=dao.query(user);
        if(results==null){
            throw new UserException("操作失败，请重试！");
        }
        return results;
    }
    public void changepass(User user) throws UserException{
        int result=dao.updatePass(user);
        if(result==-1|| result==0){
            throw new UserException("原密码错误，请重试！");
        }
    }
    public void changeinfo(User user) throws UserException{
        int result=dao.updateInfo(user);
        if(result==-1|| result==0){
            throw new UserException("操作失败，请重试！");
        }
    }
}
