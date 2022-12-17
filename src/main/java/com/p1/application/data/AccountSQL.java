package com.p1.application.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Class AccountSQL.
 */
public class AccountSQL {
	
	/**
	 * Creates the account SQL.
	 */
	public static void createAccountSQL(){
		Connection connection = null; 
		
		try{
			connection = ConnectionJDBC.getConnection("AccountDB.sqlite");
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			statement.executeUpdate("DROP TABLE IF EXISTS Users");
			statement.executeUpdate("CREATE TABLE Users " 
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "UserName VARCHAR(50) NOT NULL UNIQUE, "
					+ "Password VARCHAR(50) NOT NULL, "
					+ "Email varchar(50) NOT NULL UNIQUE, "
					+ "Favorites VARCHAR(100), "
					+ "ZIP INTEGER, "
					+ "GPA FLOAT, "
					+ "SAT FLOAT, "
					+"ACT FLOAT, "
					+"FILE BLOB "
					+ ")");
			
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
