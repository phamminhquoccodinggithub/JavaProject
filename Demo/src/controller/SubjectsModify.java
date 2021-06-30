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
import model.Subjects;
import model.DBConnection;

/**
 *
 * @author Quoc
 */
public class SubjectsModify {
    public static List<Subjects> findAll() {
        List<Subjects> subjectsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("Select * from subjects");

            rs = pst.executeQuery();
            while (rs.next()) {
                Subjects sb = new Subjects(rs.getString("ID"), rs.getString("Name"), rs.getFloat("Credit"), rs.getFloat("Fee"));
                subjectsList.add(sb);
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
            Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return subjectsList;
    }

    public static void insert(Subjects sb) {
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement check = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            conn = DBConnection.connect();
            check = conn.prepareStatement("SELECT ID FROM subjects WHERE ID = ?");
            check.setString(1, sb.getId());
            rs =check.executeQuery();
            if(rs.next()) 
            {
                JOptionPane.showMessageDialog(null, "ID is exist");
            }
            else{
                pst = conn.prepareStatement("insert into subjects (ID, Name, Credit, Fee) values(?,?,?,?)");
            pst.setString(1, sb.getId());
            pst.setString(2, sb.getName());
            pst.setFloat(3, sb.getCredit());
            pst.setFloat(4, sb.getFee());  
            pst.execute();
            JOptionPane.showMessageDialog(null, "Inserted sucessfully");
            }

        } catch (SQLException e) {
            Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void update(Subjects sb) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("update subjects set Name=?, Credit=?, Fee=? where ID = ?");
            pst.setString(4, sb.getId());
            pst.setString(1, sb.getName());
            pst.setFloat(2, sb.getCredit());
            pst.setFloat(3,sb.getFee());
            pst.execute();

        } catch (SQLException e) {
            Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void delete(String id) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("delete from subjects where ID =?");
            pst.setString(1, id);

            pst.execute();

        } catch (SQLException e) {
            Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static List<Subjects> search(Subjects sb) {
        List<Subjects> subjectsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("select * from subjects where ID like ? or Name = ? ");
            pst.setString(1, sb.getId());
            pst.setString(2, sb.getName());
            
            rs = pst.executeQuery();
            while (rs.next()) {
                sb = new Subjects(rs.getString("ID"), rs.getString("Name"), rs.getFloat("Credit"), rs.getFloat("Fee"));
                subjectsList.add(sb);
            }

        } catch (SQLException e) {
            Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(SubjectsModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return subjectsList;
    }
}

