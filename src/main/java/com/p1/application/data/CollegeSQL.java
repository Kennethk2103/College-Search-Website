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
			statement.executeUpdate("CREATE TABLE Colleges (ID INTEGER PRIMARY KEY ,Name VARCHAR(500) NOT NULL);");

			

	
			// ResultSet rs = statement.executeQuery("Select * FROM Users");
			
			// while(rs.next()) {
			// 	System.out.println("\nFirst Name: " + rs.getString("FirstName"));
			// 	System.out.println("\nLast Name: " + rs.getString("LastName"));
			// 	System.out.println("---------------------------------------");
			// }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
    }
    public static void insertIntoSQlCollege(int id, String name){
        Connection connection = null; 
        try{
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");
            System.out.println("Connection made");

            PreparedStatement preState = connection.prepareStatement("INSERT INTO Colleges(ID, Name) VALUES (?, ?)");
            System.out.println("Statement made");

            preState.setInt(1, id);
            preState.setString(2, name);
            System.out.println("Set data");
            preState.executeUpdate();
            System.out.println("Executed");


			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
    }
   
}
