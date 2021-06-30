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
import model.FeeModel;

/**
 *
 * @author Quoc
 */
public class FeeModify {
     public static List<FeeModel> findAll() {
        List<FeeModel> feeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("Select * from fees");

            rs = pst.executeQuery();
            while (rs.next()) {
                FeeModel fe = new FeeModel(rs.getInt("student_id"), rs.getString("subject_id"),  rs.getFloat("fee_paid"), rs.getString("date_paid"));
                feeList.add(fe);
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
            Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return feeList;
    }

    public static void insert(FeeModel fe) {
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement check = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            check = conn.prepareStatement("SELECT * FROM fees WHERE student_id = ? and subject_id = ?");
            check.setInt(1, fe.getStudent_id());
            check.setString(2, fe.getSubject_id());
            rs =check.executeQuery();
            if(rs.next()) 
            {
                JOptionPane.showMessageDialog(null, "ID is existed");
            }
            pst = conn.prepareStatement("insert into fees (student_id, subject_id, fee_paid, date_paid) values(?,?,?,?)");
            pst.setInt(1, fe.getStudent_id());
            pst.setString(2, fe.getSubject_id());          
            pst.setFloat(3, fe.getPaid());
            pst.setString(4, fe.getDate_paid());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Inserted sucessfully");
        } catch (SQLException e) {
            Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void update(FeeModel fe) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("update fees set subject_id = ?, fee_paid=?, date_paid=? where subject_id = ? and student_id = ? ");
            pst.setFloat(1, fe.getPaid());
            pst.setString(2, fe.getDate_paid());
            pst.setInt(4, fe.getStudent_id());
            pst.setString(3, fe.getSubject_id());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated sucessfully");
        } catch (SQLException e) {
            Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void delete(int student_id, String subject_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("delete from fees where student_id =? and subject_id like? ");
            pst.setInt(1, student_id);
            pst.setString(2, subject_id);
            pst.execute();

        } catch (SQLException e) {
            Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(FeeModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static List<FeeModel> search(FeeModel fe) {
        List<FeeModel> feeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("select * from fees where student_id like ? or subject_id like ?");
            pst.setInt(1, fe.getStudent_id());
            pst.setString(2, fe.getSubject_id());
            rs = pst.executeQuery();
            while (rs.next()) {
                fe = new FeeModel(rs.getInt("student_id"),  rs.getString("subject_id"),  rs.getFloat("fee_paid"), rs.getString("date_paid"));
                feeList.add(fe);
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
        return feeList;
    }
}
