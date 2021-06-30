/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import model.DBConnection;
import model.Student;

/**
 *
 * @author Quoc
 */
public class StudentModify {

    public static List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("Select * from student");

            rs = pst.executeQuery();
            while (rs.next()) {
                Student std = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getString("address"),
                        rs.getString("dob"), rs.getString("mail"), rs.getInt("phone"), rs.getString("classid"));
                studentList.add(std);
            }
            /*try{
                if(conn != null){
                    conn.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            }catch(Exception ex2){
                ex2.printStackTrace();
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        } catch (SQLException e) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return studentList;
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
   
    public static void insert(Student std) {
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement check = null;
        ResultSet rs = null;
        try {            
            conn = DBConnection.connect();
            check = conn.prepareStatement("SELECT id FROM student WHERE id = ?");
            check.setInt(1, std.getId());
            rs =check.executeQuery();
            if(rs.next()) 
            {
                JOptionPane.showMessageDialog(null, "ID is existed");
            }
            else{
                if(!isValid(std.getMail())){
                JOptionPane.showMessageDialog(null, "Email is not valid");
            } 
                else{
                    pst = conn.prepareStatement("insert into student (id, name, phone, mail, address, dob, gender, classid) values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, std.getId());
            pst.setString(2, std.getName());
            pst.setInt(3, std.getPhone());
            pst.setString(4, std.getMail());            
            pst.setString(5, std.getAddress());
            pst.setString(6, std.getDob());
            pst.setString(7, std.getGender());
            pst.setString(8, std.getClassid());  
            pst.execute();
            JOptionPane.showMessageDialog(null, "Inserted sucessfully");
                }
                
            }
            

        } catch (SQLException e) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void update(Student std) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            if(!isValid(std.getMail())){
                JOptionPane.showMessageDialog(null, "Email is not valid");
            }else{
                pst = conn.prepareCall("update student set name=?, phone=?, mail=?, address=?, dob=?, gender=?, classid=? where id = ?");
            pst.setInt(8, std.getId());
            pst.setString(1, std.getName());
            pst.setInt(2, std.getPhone());
            pst.setString(3, std.getMail());
            pst.setString(4, std.getAddress());
            pst.setString(5, std.getDob());
            pst.setString(6, std.getGender());
            pst.setString(7, std.getClassid());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated sucessfully");
            }
            
        } catch (SQLException e) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void delete(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("delete from student where id =?");
            pst.setInt(1, id);

            pst.execute();

        } catch (SQLException e) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static List<Student> search(Student std) {
        List<Student> studentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("select * from student where id like ? or name like ? or classid like ?");
            pst.setInt(1, std.getId());
            pst.setString(2, std.getName());
            pst.setString(3, std.getClassid());
            rs = pst.executeQuery();
            while (rs.next()) {
                std = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getString("address"),
                        rs.getString("dob"), rs.getString("mail"), rs.getInt("phone"), rs.getString("classid"));
                studentList.add(std);
            }

        } catch (SQLException e) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return studentList;
    }
}
