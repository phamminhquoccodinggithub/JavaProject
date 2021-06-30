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
public class FeeModel {
    private int student_id;
    private String subject_id, date_paid;
    private float  paid ;

    public FeeModel(int student_id, String subject_id, float paid, String date_paid) {
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.paid = paid;
        this.date_paid = date_paid;
        
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getDate_paid() {
        return date_paid;
    }

    public void setDate_paid(String date_paid) {
        this.date_paid = date_paid;
    }

    

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }


    
}
