package com.p1.application.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public static Connection getConnection(String databaseName) {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + databaseName);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    public static boolean closeConnection(Connection conn) {
        try {
            if(conn != null) {
                conn.close();
                return true; 
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return false; 
    }

}