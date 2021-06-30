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
public class Student extends Person{
    private String classid;
    
    public Student(int id, String name, String gender, String address, String dob, String mail, int phone, String classid) {
        super(id, name, gender, address, dob, mail, phone);
        this.classid = classid;
        
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    
    
    
}
