/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quang
 */
public class TeacherDAO extends MyDAO {

    public List<Teacher> getTeachers() {
        List<Teacher> t = new ArrayList<>();
        xSql = "select * from Teacher";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String id, name, email;
            Teacher x;
            while (rs.next()) {
                id = rs.getString(1);
                name = rs.getString(2);
                email = rs.getString(3);
                x = new Teacher(id, name, email);
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
        xSql = "select id from Teacher";
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

    public Teacher getTeacher(String id) {
        Teacher t = null;
        xSql = "select * from Teacher where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            String name, email;
            while (rs.next()) {
                name = rs.getString(2);
                email = rs.getString(3);
                t = new Teacher(id, name, email);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void update(String id, Teacher x) {
        xSql = "update Teacher set name=?, email=? where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getName());
            ps.setString(2, x.getEmail());
            ps.setString(3, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getTeachers(String colName, String sortType) {
        List<Teacher> t = new ArrayList<>();
        xSql = "select * from Teacher order by " + colName + " " + sortType;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String id, name, email;
            Teacher x;
            while (rs.next()) {
                id = rs.getString(1);
                name = rs.getString(2);
                email = rs.getString(3);
                x = new Teacher(id, name, email);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void delete(String id) {
        xSql = "delete from Teacher where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(Teacher x) {
        xSql = "insert into Teacher values (?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getId());
            ps.setString(2, x.getName());
            ps.setString(3, x.getEmail());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
