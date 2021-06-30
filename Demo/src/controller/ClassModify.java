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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DBConnection;
import model.ClassinFaculty;

/**
 *
 * @author Quoc
 */
public class ClassModify {
    public static List<ClassinFaculty> findAll() {
        List<ClassinFaculty> classList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("Select * from class");

            rs = pst.executeQuery();
            while (rs.next()) {
                ClassinFaculty cl = new ClassinFaculty(rs.getString("id"), rs.getString("name"), rs.getInt("Faculty_id"));
                classList.add(cl);
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
            Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return classList;
    }

    public static void insert(ClassinFaculty cl) {
        Connection conn = null;
        PreparedStatement pst = null;
         PreparedStatement check = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            conn = DBConnection.connect();
            conn = DBConnection.connect();
            check = conn.prepareStatement("SELECT id FROM class WHERE id = ?");
            check.setString(1, cl.getId());
            rs =check.executeQuery();
            if(rs.next()) 
            {
                JOptionPane.showMessageDialog(null, "ID is existed");
            }
            else{
                pst = conn.prepareStatement("insert into class (id, name, Faculty_id) values(?,?,?)");
            pst.setString(1, cl.getId());
            pst.setString(2, cl.getClassName());
            pst.setInt(3, cl.getFacultyID());            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Inserted sucessfully");
            }

        } catch (SQLException e) {
            Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void update(ClassinFaculty cl) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("update class set name=?, Faculty_id=? where id = ?");
            pst.setString(3, cl.getId());
            pst.setString(1, cl.getClassName());
            pst.setInt(2, cl.getFacultyID());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated sucessfully");
        } catch (SQLException e) {
            Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void delete(String id) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("delete from class where id =?");
            pst.setString(1, id);

            pst.execute();

        } catch (SQLException e) {
            Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static List<ClassinFaculty> search(ClassinFaculty cl) {
        List<ClassinFaculty> classList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("select * from class where id like ? or name like ?");
            pst.setString(1, cl.getId());
            pst.setString(2, cl.getClassName());
            rs = pst.executeQuery();
            while (rs.next()) {
                cl = new ClassinFaculty(rs.getString("id"), rs.getString("name"), rs.getInt("Faculty_id"));
                classList.add(cl);
            }

        } catch (SQLException e) {
            Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ClassModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return classList;
    }
}
