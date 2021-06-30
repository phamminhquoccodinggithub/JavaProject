/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Quoc
 */
public class UserLogIn {
    private String userName;
    private String passWord;

    public UserLogIn(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public UserLogIn() {
    }   

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
}
