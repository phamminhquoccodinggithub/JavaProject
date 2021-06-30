/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.StudentModify.isValid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import model.DBConnection;
import model.Student;
import model.Teacher;

/**
 *
 * @author Quoc
 */
public class TeacherModify {
    public static List<Teacher> findAll() {
        List<Teacher> teacherList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("Select * from teacher");

            rs = pst.executeQuery();
            while (rs.next()) {
                Teacher t = new Teacher(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getString("address"),
                        rs.getString("dob"), rs.getString("mail"), rs.getInt("phone"), rs.getInt("Faculty_id"));
                teacherList.add(t);
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
            Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return teacherList;
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
    public static void insert(Teacher t) {
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement check = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            check = conn.prepareStatement("SELECT id FROM teacher WHERE id = ?");
            check.setInt(1, t.getId());
            rs =check.executeQuery();
            if(rs.next()) 
            {
                JOptionPane.showMessageDialog(null, "ID is exist");
            }
            else{
                if(!isValid(t.getMail())){
                JOptionPane.showMessageDialog(null, "Email is not valid");}
                else{
                    pst = conn.prepareStatement("insert into teacher (id, name, phone, mail, address, dob, gender, Faculty_id) values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, t.getId());
            pst.setString(2, t.getName());
            pst.setInt(3, t.getPhone());
            pst.setString(4, t.getMail());
            pst.setString(5, t.getAddress());
            pst.setString(6, t.getDob());
            pst.setString(7, t.getGender());
            pst.setInt(8, t.getFacultyid());  
            pst.execute();
            JOptionPane.showMessageDialog(null, "Inserted sucessfully");
                }
                
            }

        } catch (SQLException e) {
            Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void update(Teacher t) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            if(!isValid(t.getMail())){
                JOptionPane.showMessageDialog(null, "Email is not valid");
            }else{
                pst = conn.prepareCall("update teacher set name=?, phone=?, mail=?, address=?, dob=?, gender=?, Faculty_id=? where id = ?");
            pst.setInt(8, t.getId());
            pst.setString(1, t.getName());
            pst.setInt(2, t.getPhone());
            pst.setString(3, t.getMail());
            pst.setString(4, t.getAddress());
            pst.setString(5, t.getDob());
            pst.setString(6, t.getGender());
            pst.setInt(7, t.getFacultyid());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated sucessfully");
            }
            
        } catch (SQLException e) {
            Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void delete(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("delete from teacher where id =?");
            pst.setInt(1, id);

            pst.execute();

        } catch (SQLException e) {
            Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static List<Teacher> search(Teacher t) {
        List<Teacher> teacherList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("select * from teacher where id like ? or name like ? ");
            pst.setInt(1, t.getId());
            pst.setString(2, t.getName());
            rs = pst.executeQuery();
            while (rs.next()) {
                t = new Teacher(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getString("address"),
                        rs.getString("dob"), rs.getString("mail"), rs.getInt("phone"), rs.getInt("Faculty_id"));
                teacherList.add(t);
            }

        } catch (SQLException e) {
            Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(TeacherModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return teacherList;
    }
}
