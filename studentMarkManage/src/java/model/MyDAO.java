package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MyDAO extends DBContext {

    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    public String xSql = null;

    public MyDAO() {
        con = connection;
    }

    public void finalize() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getColNames(String tableName) {
        List<String> colNames = new ArrayList<>();
        xSql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                colNames.add(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (colNames);
    }

}
