package com.p1.application.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;
public class AccountSQL {
	public AccountSQL(){
		Connection connection = null; 
		
		try{
			connection = ConnectionJDBC.getConnection("C:\\Users\\Kenny\\eclipse-workspace\\Main\\MyDB.db");
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			statement.executeUpdate("DROP TABLE IF EXISTS users");
			statement.executeUpdate("CREATE TABLE users " 
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "UserName VARCHAR(50) NOT NULL UNIQUE, "
					+ "Password VARCHAR(50) NOT NULL, "
					+ "Email varchar(50) NOT NULL UNIQUE, "
					+ "Favorites VARCHAR(100), "
					+ ")");
			
			statement.executeUpdate("INSERT INTO users "
									+ "(UserName, Password, Email, Favorites) "
									+ "VALUES('John', 'Doe', 'jdoe' , '')");
			
	
			ResultSet rs = statement.executeQuery("Select * FROM Users");
			
			while(rs.next()) {
				System.out.println("\nFirst Name: " + rs.getString("FirstName"));
				System.out.println("\nLast Name: " + rs.getString("LastName"));
				System.out.println("\nUsername: " + rs.getString("UserName"));
				System.out.println("\nPassword: " + rs.getString("Password"));
				System.out.println("---------------------------------------");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
	}
}
