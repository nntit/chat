/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author nnt12
 */
public class Myconnect {

    public Connection getConnection() {
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost/quanlytaikhoan?autoReconnect=true&useSSL=false";
            String USER = "root";
            String PASS = "";
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (Exception e) {
            return null;
        }
    }
}
