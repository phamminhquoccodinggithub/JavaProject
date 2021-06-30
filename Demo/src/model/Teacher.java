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
public class Teacher extends Person{
    private int facultyid;

    public int getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(int facultyid) {
        this.facultyid = facultyid;
    }
    public Teacher(int id, String sname, String gender, String address, String dob, String mail, int phone, int facultyid) {
        super(id, sname, gender, address, dob, mail, phone);
        this.facultyid = facultyid;
    }
    
}
