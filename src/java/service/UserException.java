/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author JasonLin
 */
public class UserException extends RuntimeException{
    public UserException(String message)
    {
        super(message);
    }
    public String getMessage(String message){
        return message;
    }
}
