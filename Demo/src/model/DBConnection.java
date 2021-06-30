/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Component;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Quoc
 */
public class DBConnection {

    public static Connection connect() {
        /*Connection conn = null;     
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/loginregister?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=GMT","root","123456");
            Component rootPane = null;
            System.out.println("Connected");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return conn;*/
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String unicode = "useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
            return DriverManager.getConnection("jdbc:mysql://localhost:3307/javaproject?" + unicode, "root", "");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }
}
