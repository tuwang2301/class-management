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
public class SubjectDAO extends MyDAO {

    public List<Subject> getSubjects() {
        List<Subject> t = new ArrayList<>();
        TeacherDAO tD = new TeacherDAO();
        xSql = "select * from Subject";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String id, tid, name;
            Subject x;
            while (rs.next()) {
                id = rs.getString(1);
                tid = rs.getString(2);
                name = rs.getString(3);
                x = new Subject(id, tD.getTeacher(tid), name);
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
        xSql = "select id from Subject";
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

    public Subject getSubject(String id) {
        Subject s = null;
        TeacherDAO tD = new TeacherDAO();
        xSql = "select * from Subject where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            String tid, name;
            while (rs.next()) {
                tid = rs.getString(2);
                name = rs.getString(3);
                s = new Subject(id, tD.getTeacher(tid), name);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (s);
    }

    public Subject getSubjectByTeacher(String tid) {
        Subject s = new Subject();
        TeacherDAO tD = new TeacherDAO();
        xSql = "select * from Subject where tid=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, tid);
            rs = ps.executeQuery();
            String id, name;
            while (rs.next()) {
                id = rs.getString(1);
                name = rs.getString(3);
                s = new Subject(id, tD.getTeacher(tid), name);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (s);
    }

    public void update(String id, Subject x) {
        xSql = "update Subject set tid=?, name=? where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getTeacher().getId());
            ps.setString(2, x.getName());
            ps.setString(3, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Subject> getSubjects(String colName, String sortType) {
        List<Subject> t = new ArrayList<>();
        TeacherDAO tD = new TeacherDAO();
        xSql = "select * from Subject order by " + colName + " " + sortType;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String id, tid, name;
            Subject x;
            while (rs.next()) {
                id = rs.getString(1);
                tid = rs.getString(2);
                name = rs.getString(3);
                x = new Subject(id, tD.getTeacher(tid), name);
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
        xSql = "delete from Subject where id=?";
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
   

    public void insert(Subject x) {
        xSql = "insert into Subject values (?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getId());
            ps.setString(2, x.getTeacher().getId());
            ps.setString(3, x.getName());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
