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
public class MarkDAO extends MyDAO {
//    public static void main(String[] args) {
//       MarkDAO mD = new MarkDAO();
//       List<Mark> lst = mD.getMarksBySID("MAS291");
//        for (Mark mark : lst) {
//            System.out.println(mark.toString());
//        }
//    }

    public List<Mark> getMarks() {
        StudentDAO stD = new StudentDAO();
        SubjectDAO sD = new SubjectDAO();
        List<Mark> t = new ArrayList<>();
        xSql = "select * from SubjectMark";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String sid, stid;
            float gpa;
            Mark x;
            while (rs.next()) {
                sid = rs.getString(1);
                stid = rs.getString(2);
                gpa = rs.getFloat(3);
                x = new Mark(sD.getSubject(sid), stD.getStudent(stid), gpa);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Mark getMark(String subjectID, String studentID) {
        StudentDAO stD = new StudentDAO();
        SubjectDAO sD = new SubjectDAO();
        float gpa;
        Mark x = null;
        xSql = "select * from SubjectMark where sid = ? and stid = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, subjectID);
            ps.setString(2, studentID);
            rs = ps.executeQuery();
            while (rs.next()) {
                gpa = rs.getFloat(3);
                x = new Mark(sD.getSubject(subjectID), stD.getStudent(studentID), gpa);
                return x;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public List<Mark> getMarksBySID(String subjectID) {
        StudentDAO stD = new StudentDAO();
        SubjectDAO sD = new SubjectDAO();
        List<Mark> t = new ArrayList<>();
        xSql = "select * from SubjectMark where sid = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, subjectID);
            rs = ps.executeQuery();
            String stid;
            float gpa;
            Mark x;
            while (rs.next()) {
                stid = rs.getString(2);
                gpa = rs.getFloat(3);
                x = new Mark(sD.getSubject(subjectID), stD.getStudent(stid), gpa);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Mark> getMarksByStID(String studentID) {
        StudentDAO stD = new StudentDAO();
        SubjectDAO sD = new SubjectDAO();
        List<Mark> t = new ArrayList<>();
        xSql = "select * from SubjectMark where stid = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, studentID);
            rs = ps.executeQuery();
            String sid;
            float gpa;
            Mark x;
            while (rs.next()) {
                sid = rs.getString(1);
                gpa = rs.getFloat(3);
                x = new Mark(sD.getSubject(sid), stD.getStudent(studentID), gpa);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void deleteStid(String stid) {
        xSql = "delete from SubjectMark where stid=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, stid);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void deleteSid(String sid) {
        xSql = "delete from SubjectMark where sid=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, sid);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Mark> getMarks(String colName, String sortType) {
        SubjectDAO sD = new SubjectDAO();
        StudentDAO stD = new StudentDAO();
        List<Mark> t = new ArrayList<>();
        xSql = "select * from SubjectMark order by " + colName + " " + sortType;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String sid, stid;
            float gpa;
            Mark x;
            while (rs.next()) {
                sid = rs.getString(1);
                stid = rs.getString(2);
                gpa = rs.getFloat(3);
                x = new Mark(sD.getSubject(sid), stD.getStudent(stid), gpa);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void update(String sid, String stid, float x) {
        xSql = "update SubjectMark set gpa = " + x + " where sid=? and stid=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, sid);
            ps.setString(2, stid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
         public void insert(String sid, String stid) {
        xSql = "insert into SubjectMark (sid,stid) values (?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, sid);
            ps.setString(2, stid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
