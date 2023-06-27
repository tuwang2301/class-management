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
public class UserDAO extends MyDAO {

    public List<User> getUsers() {
        List<User> t = new ArrayList<>();
        xSql = "select * from Users";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String id, username, password;
            int role;
            User x;
            while (rs.next()) {
                id = rs.getString(1);
                username = rs.getString(2);
                password = rs.getString(3);
                role = rs.getInt(4);
                x = new User(id, username, password, role);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public User getUser(String username, String password) {
        User u = null;
        if (username == null || password == null
                || username.trim().isEmpty() || password.trim().isEmpty()) {
            return null;
        }
        xSql = "select * from Users where username=? and password=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            String id;
            int role;
            while (rs.next()) {
                id = rs.getString(1);
                role = rs.getInt(4);
                u = new User(id, username, password, role);
                return u;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (u);
    }

    public User getUserByID(String id) {
        User u = null;
        if (id == null) {
            return null;
        }
        xSql = "select * from Users where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            String username, password;
            int role;
            while (rs.next()) {
                username = rs.getString(2);
                password = rs.getString(3);
                role = rs.getInt(4);
                u = new User(id, username, password, role);
                return u;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (u);
    }
    
    public User getUserByUsername(String username) {
        User u = null;
        if (username == null) {
            return null;
        }
        xSql = "select * from Users where username = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            String id, password;
            int role;
            while (rs.next()) {
                id = rs.getString(1);
                password = rs.getString(3);
                role = rs.getInt(4);
                u = new User(id, username, password, role);
                return u;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (u);
    }

    public void deleteByID(String id) {
        xSql = "delete from Users where id=?";
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

    public List<User> getUsers(String colName, String sortType) {
        List<User> t = new ArrayList<>();
        xSql = "select * from Users order by " + colName + " " + sortType;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String id, username, password;
            int role;
            User x;
            while (rs.next()) {
                id = rs.getString(1);
                username = rs.getString(2);
                password = rs.getString(3);
                role = rs.getInt(4);
                x = new User(id, username, password, role);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void update(String id, User x) {
        xSql = "update Users set password=?, role=? where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getPassword());
            ps.setInt(2, x.getRole());
            ps.setString(3, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(User x) {
        xSql = "insert into Users values (?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getId());
            ps.setString(2, x.getUsername());
            ps.setString(3, x.getPassword());
            ps.setInt(4, x.getRole());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
