/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quang
 */
public class StudentDAO extends MyDAO {

    public List<Student> getStudents() {
        List<Student> t = new ArrayList<>();
        xSql = "select * from Student";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String id, name, email;
            Date dob;
            boolean gender;
            Student x;
            while (rs.next()) {
                id = rs.getString(1);
                name = rs.getString(2);
                email = rs.getString(3);
                dob = rs.getDate(4);
                gender = rs.getBoolean(5);
                x = new Student(id, name, email, dob, gender);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
        public List<String> getIds() {
        List<String> t = new ArrayList<>();
        xSql = "select id from Student";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String id;
            while (rs.next()) {
                id = rs.getString(1);
                t.add(id);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Student getStudent(String id) {
        Student s = null;
        xSql = "select * from Student where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            String name, email;
            Date dob;
            boolean gender;
            while (rs.next()) {
                name = rs.getString(2);
                email = rs.getString(3);
                dob = rs.getDate(4);
                gender = rs.getBoolean(5);
                s = new Student(id, name, email, dob, gender);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (s);
    }


    public List<Student> getStudents(String colName, String sortType) {
        List<Student> t = new ArrayList<>();
        xSql = "select * from Student order by " + colName + " " + sortType;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String id, name, email;
            Date dob;
            boolean gender;
            Student x;
            while (rs.next()) {
                id = rs.getString(1);
                name = rs.getString(2);
                email = rs.getString(3);
                dob = rs.getDate(4);
                gender = rs.getBoolean(5);
                x = new Student(id, name, email, dob, gender);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void update(String id, Student x) {
        xSql = "update Student set name=?, email=?, dob=?, gender=? where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getName());
            ps.setString(2, x.getEmail());
            ps.setDate(3, x.getDob());
            ps.setBoolean(4, x.getGender());
            ps.setString(5, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(String id) {
     xSql = "delete from Student where id=?";
     try {
        ps = con.prepareStatement(xSql);
        ps.setString(1, id);
        ps.executeUpdate();
        //con.commit();
        ps.close();
     }
     catch(Exception e) {
        e.printStackTrace();
     }
  }
     public void insert(Student x) {
        xSql = "insert into Student values (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getId());
            ps.setString(2, x.getName());
            ps.setString(3, x.getEmail());
            ps.setDate(4, x.getDob());
            ps.setBoolean(5, x.getGender());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
