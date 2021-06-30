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
import model.ModelScore;

/**
 *
 * @author Quoc
 */
public class ScoreModify {
    public static List<ModelScore> findAll() {
        List<ModelScore> scoreList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("Select * from score");

            rs = pst.executeQuery();
            while (rs.next()) {
                ModelScore sc = new ModelScore(rs.getInt("student_id"), rs.getString("subject_id"), rs.getFloat("Score"));
                scoreList.add(sc);
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
            Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return scoreList;
    }

    public static void insert(ModelScore fa) {
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement check = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            check = conn.prepareStatement("SELECT * FROM score WHERE student_id = ? and subject_id=?");
            check.setInt(1, fa.getStudentid());
            check.setString(2, fa.getSubjectid());
            rs =check.executeQuery();
            if(rs.next()) 
            {
                JOptionPane.showMessageDialog(null, "ID is existed");
            }
            else{
                pst = conn.prepareStatement("insert into score (student_id, subject_id, Score) values(?,?,?)");
            pst.setInt(1, fa.getStudentid());
            pst.setString(2, fa.getSubjectid());
            pst.setFloat(3, fa.getScore());            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Inserted sucessfully");
            }

        } catch (SQLException e) {
            Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void update(ModelScore sc) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("update score set score=? where student_id = ? and subject_id = ?");
            pst.setFloat(1, sc.getScore());
            pst.setInt(2, sc.getStudentid());
            pst.setString(3, sc.getSubjectid());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated sucessfully");
        } catch (SQLException e) {
            Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void delete(int student_id, String subject_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("delete from score where student_id =? and subject_id like? ");
            pst.setInt(1, student_id);
            pst.setString(2, subject_id);
            pst.execute();

        } catch (SQLException e) {
            Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(ScoreModify.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static List<ModelScore> search(ModelScore sc) {
        List<ModelScore> scoreList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareCall("select * from score where student_id like ? or subject_id like ?");
            pst.setInt(1, sc.getStudentid());
            pst.setString(2, sc.getSubjectid());
            rs = pst.executeQuery();
            while (rs.next()) {
                sc = new ModelScore(rs.getInt("student_id"), rs.getString("subject_id"), rs.getFloat("Score"));
                scoreList.add(sc);
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
        return scoreList;
    }
}
