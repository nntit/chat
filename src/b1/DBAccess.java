/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author nnt12
 */
public class DBAccess {

    private Connection con;
    private Statement stmt;

    private Connection getConnection() {
        try {
            String DB_URL = "jdbc:mysql://localhost/quanlytaikhoan?autoReconnect=true&useSSL=false";
            String USER = "root";
            String PASS = "";
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (Exception e) {
            return null;
        }
    }

    public DBAccess() {
        try {
            con = getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
        }
    }

    public int Update(String sql) {
        try {
            int i = stmt.executeUpdate(sql);
            return i;
        } catch (Exception e) {
            return -1;
        }
    }

    public ResultSet Query(String sql) {
        try {
            ResultSet i = stmt.executeQuery(sql);
            return i;
        } catch (Exception e) {
            return null;
        }
    }
}
