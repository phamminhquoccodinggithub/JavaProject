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
import model.FacultyModel;


/**
 *
 * @author Quoc
 */
public class FacultyModify {
    public static List<FacultyModel> findAll() {
        List<FacultyModel> facultyList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("Select * from faculty");

            rs = pst.executeQuery();
            while (rs.next()) {
                FacultyModel fa = new FacultyModel(rs.getInt("Faculty_id"), rs.getInt("Contact"), rs.getString("Faculty_name"));
                facultyList.add(fa);
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
            Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return facultyList;
    }

    public static void insert(FacultyModel fa) {
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement check = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            conn = DBConnection.connect();
            check = conn.prepareStatement("SELECT Faculty_id FROM faculty WHERE Faculty_id = ?");
            check.setInt(1, fa.getId());
            rs =check.executeQuery();
            if(rs.next()) 
            {
                JOptionPane.showMessageDialog(null, "ID is existed");
            }
            else{
                pst = conn.prepareStatement("insert into faculty (Faculty_id, Faculty_name, Contact) values(?,?,?)");
            pst.setInt(1, fa.getId());
            pst.setString(2, fa.getFacultyName());
            pst.setInt(3, fa.getContact());            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Inserted sucessfully");
            }

        } catch (SQLException e) {
            Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void update(FacultyModel fa) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("update student set Faculty_name=?, Contact=? where Faculty_id = ?");
            pst.setInt(3, fa.getId());
            pst.setString(1, fa.getFacultyName());
            pst.setInt(2, fa.getContact());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated sucessfully");
        } catch (SQLException e) {
            Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void delete(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("delete from faculty where Faculty_id =?");
            pst.setInt(1, id);

            pst.execute();

        } catch (SQLException e) {
            Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static List<FacultyModel> search(FacultyModel fa) {
        List<FacultyModel> facultyList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("select * from faculty where Faculty_id like ? or Faculty_name like ?");
            pst.setInt(1, fa.getId());
            pst.setString(2, fa.getFacultyName());
            rs = pst.executeQuery();
            while (rs.next()) {
                fa = new FacultyModel(rs.getInt("Faculty_id"), rs.getInt("Contact"), rs.getString("Faculty_name"));
                facultyList.add(fa);
            }

        } catch (SQLException e) {
            Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(FacultyModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return facultyList;
    }
    
}

