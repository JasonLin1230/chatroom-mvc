/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

/**
 *
 * @author JasonLin
 */
public class User {
    private String id;                //id
    private String username;          //用户名
    private String old_password;      //旧密码
    private String password;          //密码
    private String identity;          //身份
   
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getOldPassword() {
        return old_password;
    }
    public void setOldPassword(String old_password) {
        this.old_password = old_password;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getIdentity() {
        return identity;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
