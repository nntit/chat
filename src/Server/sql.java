/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import obj.chanel;
import obj.friend;

/**
 *
 * @author nnt12
 */
public class sql {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/chat?autoReconnect=true&useSSL=false";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public String ruuid() {
        UUID uid = UUID.randomUUID();
        return uid.toString();
    }

    public int Update(String sql) {
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return rs;
        } catch (Exception ex) {
            System.out.println(ex);
            return -1;
        }
    }

    public boolean isMailNull(String sql) {
        boolean temp = false;
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM user where mail = '" + sql + "'");
            if (rs.next()) {
                temp = false;
            } else {
                temp = true;
            }

            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }

    public String login(String mail, String pass) {
        String temp = null;
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM user where mail = '" + mail + "' and pass ='" + pass + "'");
            if (rs.next()) {
                temp = rs.getString("id");
            } else {
                temp = null;
            }

            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }

    public String username(String id) {
        String temp = null;
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM user where id = '" + id + "'");
            if (rs.next()) {
                temp = rs.getString("name");
            } else {
                temp = null;
            }

            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }

    public String username(String session, String pass) {
        String temp = null;
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM user where session = '" + session + "' and pass = '" + pass + "'");
            if (rs.next()) {
                temp = rs.getString("name");
            } else {
                temp = null;
            }

            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }

    public String user_session_to_id(String session) {
        String temp = null;
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM user where session = '" + session + "'");
            if (rs.next()) {
                temp = rs.getString("id");
            } else {
                temp = null;
            }

            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }

    public String user_mail_to_id(String mail) {
        String temp = null;
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM user where mail = '" + mail + "'");
            if (rs.next()) {
                temp = rs.getString("id");
            } else {
                temp = null;
            }

            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }

    public ArrayList<chanel> listchanel() {
        ArrayList<chanel> temp = new ArrayList<>();
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM chanel");
            while (rs.next()) {
                chanel cl = new chanel(rs.getString("id"), rs.getString("name"));
                temp.add(cl);
            }
            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }

    public ArrayList<friend> listfriend(String user) {
        ArrayList<friend> temp = new ArrayList<>();
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM friend where f1 = '" + user + "' and Status = 1");
            while (rs.next()) {
                friend cl = new friend(rs.getString("id"),rs.getString("f1"), rs.getString("f2"), Integer.parseInt(rs.getString("Status")));
                temp.add(cl);
            }
            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }

    public boolean kiemtrafriendNull(String f1, String f2) {
        boolean temp = false;
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM friend where f1 = '" + f1 + "' and f2 = '" + f2 + "'");
            if (rs.next()) {
                temp = false;
            } else {
                temp = true;
            }

            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }
    
    public int kiemtrafriendToStatus(String f1, String f2) {
        int temp = -1;
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM friend where f1 = '" + f1 + "' and f2 = '" + f2 + "'");
            if (rs.next()) {
                temp = Integer.valueOf(rs.getString("Status"));
            } else {
            }
            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }    
    public String kiemtrafriendToid(String f1, String f2) {
        String temp = "";
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM friend where f1 = '" + f1 + "' and f2 = '" + f2 + "'");
            if (rs.next()) {
                temp = rs.getString("id");
            } else {
            }
            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }
    
    public ArrayList<friend> listthumoifriend(String user) {
        ArrayList<friend> temp = new ArrayList<>();
        try {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM friend where f2 = '" + user + "' and Status =0");
            while (rs.next()) {
                friend cl = new friend(rs.getString("id"),rs.getString("f1"), rs.getString("f2"), Integer.parseInt(rs.getString("Status")));
                temp.add(cl);
            }
            stmt.close();
            conn.close();
            return temp;
        } catch (Exception ex) {
            System.out.println(ex);
            return temp;
        }
    }
    /*
    while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("id");
         int age = rs.getInt("age");
         String first = rs.getString("first");
         String last = rs.getString("last");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", Age: " + age);
         System.out.print(", First: " + first);
         System.out.println(", Last: " + last);
      }
     */
}
