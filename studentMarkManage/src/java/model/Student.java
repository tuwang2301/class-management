/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author quang
 */
public class Student {
    private String id, name, email;
    private Date dob;
    private boolean gender;

    public Student() {
    }

    public Student(String id, String name, String email, Date dob, boolean gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", gender=" + gender + '}';
    }
    
    
}
