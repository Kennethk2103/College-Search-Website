
package com.p1.application.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.JsonNode;


/**
 * The Class CollegeSQL.
 */
public class CollegeSQL {
	
	/**
	 * Creates the college SQL.
	 */
	public static void createCollegeSQL() {

		Connection connection = null;
		GetSearchTerms.setUp();
		LinkedList<LinkedList<String>> SearchTerms = GetSearchTerms.getTerms();
		try {
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);

			statement.executeUpdate("DROP TABLE IF EXISTS Colleges");
			String update = "CREATE TABLE Colleges (ID INTEGER PRIMARY KEY , latest_school_name VARCHAR(500) NOT NULL, ";
			for (int i = 2; i < SearchTerms.size(); i++) {
				if ((i + 1) == SearchTerms.size()) {
					update += SearchTerms.get(i).get(0) + " " + SearchTerms.get(i).get(1).toUpperCase() + ")";
				} else {
					update += SearchTerms.get(i).get(0) + " " + SearchTerms.get(i).get(1).toUpperCase() + ", ";
				}
			}
			update = update.replace(".", "_");
			update = update.replace("STRING", "VARCHAR(500)");
			update = update.replace("AUTOCOMPLETE", "VARCHAR(500)");
			statement.executeUpdate(update);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionJDBC.closeConnection(connection);
		}
	}

	/**
	 * Insert into S ql college.
	 *
	 * @param nodeList the node list
	 * @param objectName the object name
	 */
	public static void insertIntoSQlCollege(LinkedList<JsonNode> nodeList, LinkedList<LinkedList<String>> objectName) {
		Connection connection = null;
		try {
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");

			String start = "INSERT INTO COLLEGES(";
			for (int i = 0; i < objectName.size(); i++) {
				if (i == (objectName.size() - 1)) {
					start = start + objectName.get(i).get(0).replace(".", "_") + ") VALUES (";
				} else {
					start = start + objectName.get(i).get(0).replace(".", "_") + ", ";
				}

			}
			for (int j = 0; j < objectName.size(); j++) {
				if (j == (objectName.size() - 1)) {
					start += "?)";
				} else {
					start += "?, ";
				}
			}
			start.replace(".", "");
			PreparedStatement preState = connection.prepareStatement(start);
			
			for (int i = 0; i < nodeList.size(); i++) {
				if (objectName.get(i).get(1).equals("integer")) {
					preState.setInt(i + 1, nodeList.get(i).asInt());
				} else if (objectName.get(i).get(1).equals("float")) {
					preState.setFloat(i + 1, (float) nodeList.get(i).asDouble());
				} else if (objectName.get(i).get(1).contains("string")
						|| objectName.get(i).get(1).contains("autocomplete")) {
					preState.setString(i + 1, nodeList.get(i).asText().replaceAll("[^a-zA-Z ]", ""));
				}
			}
			preState.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionJDBC.closeConnection(connection);
		}

	}
}
