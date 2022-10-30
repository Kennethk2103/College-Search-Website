package com.p1.application.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class CollegeSQL {
    public static void createCollegeSQL(){
        Connection connection = null; 
		try{
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			statement.executeUpdate("DROP TABLE IF EXISTS Colleges");
			statement.executeUpdate("CREATE TABLE Colleges (ID INTEGER PRIMARY KEY , Name VARCHAR(500) NOT NULL,Read25 FLOAT ,Read75 FLOAT ,Math25 FLOAT ,Math75 FLOAT)");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
    }
    public static void insertIntoSQlCollege(int id, String name, Double read25, Double read75, Double math25, Double math75){
        Connection connection = null; 
        try{
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");

            PreparedStatement preState = connection.prepareStatement("INSERT INTO Colleges(ID, Name, Read25, Read75, Math25, Math75) VALUES (?, ?, ?, ?, ?, ?)");

            preState.setInt(1, id);
            preState.setString(2, name);
			preState.setDouble(3, read25);
			preState.setDouble(4, read75);
			preState.setDouble(5, math25);
			preState.setDouble(6, math75);
            preState.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
    }
   
}
