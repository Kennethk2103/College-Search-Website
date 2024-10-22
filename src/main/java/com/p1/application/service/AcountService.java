package com.p1.application.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.p1.application.data.Account;
import com.p1.application.data.CollegeBundle;
import com.p1.application.data.ConnectionJDBC;


/**
 * The Class AcountService.
 */
@Service

public class AcountService {

	/**
	 * Gets the account.
	 *
	 * @param idOfUser the id of user
	 * @return the account
	 */
	public static Account getAccount(int idOfUser) {
		Connection connection = null;
		Account account = null;

		try {
			connection = ConnectionJDBC.getConnection("AccountDB.sqlite");
			PreparedStatement preState = connection.prepareStatement("SELECT * FROM users WHERE ID LIKE " + idOfUser);
			ResultSet rs = preState.executeQuery();
			account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			account.setZip(rs.getInt(6));
			account.setGPA(rs.getDouble(7));
			account.setSAT(rs.getDouble(8));
			account.setACT(rs.getDouble(9));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionJDBC.closeConnection(connection);
		}
		return account;
	}

	/**
	 * Gets the account by email.
	 *
	 * @param email the email
	 * @return the account by email
	 */
	public static Account getAccountByEmail(String email) {
		Connection connection = null;
		Account account = null;

		try {
			connection = ConnectionJDBC.getConnection("AccountDB.sqlite");
			PreparedStatement preState = connection.prepareStatement("SELECT * FROM users WHERE Email LIKE '" + email + "'");
			ResultSet rs = preState.executeQuery();
			account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			account.setZip(rs.getInt(6));
			account.setGPA(rs.getDouble(7));
			account.setSAT(rs.getDouble(8));
			account.setACT(rs.getDouble(9));
			System.out.println("id " + account.getId());
			return account;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionJDBC.closeConnection(connection);
		}
		return account;
	}

	/**
	 * Update account.
	 *
	 * @param account the account
	 */
	public static void updateAccount(Account account) {
		Connection connection = null;

		try {
			connection = ConnectionJDBC.getConnection("AccountDB.sqlite");
			String conditions = "UPDATE Users SET UserName = '" + account.getUsername() + "', Password = '"
			+ account.getPassword() + "' , Email='" + account.getEmail() +
			"' , Favorites = '" + account.getFavorites() + "' , ZIP = '" + account.getZip() + "' , GPA = '"
			+ account.getGPA() + "' , SAT = '" + account.getSAT() + "' , ACT = '"
			+ account.getACT() +"' , File = '" + account.getFile()+ "' WHERE ID = " + account.getId();
			System.out.println(conditions);
			PreparedStatement preState = connection.prepareStatement(conditions);
			preState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionJDBC.closeConnection(connection);
		}

	}

	/**
	 * Adds the account.
	 *
	 * @param account the account
	 * @return true, if successful
	 */
	public static boolean AddAccount(Account account) {
	
		if(getAccountByEmail(account.getEmail()).equals(null)){
			return false;
		}
		else {
			Connection connection = null;
			try {
				connection = ConnectionJDBC.getConnection("AccountDB.sqlite");
				PreparedStatement preState = connection.prepareStatement(
						"INSERT INTO Users (UserName, Password, Email) VALUES ('" + account.getUsername() + "', '"
								+ account.getPassword() + "', '" + account.getEmail() + "' )");
				preState.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectionJDBC.closeConnection(connection);
				
			}
			return true;
		}
		
		

	}

	/**
	 * Adds the to favorites.
	 *
	 * @param idOfCollege the id of college
	 * @param account the account
	 * @return true, if successful
	 */
	public static boolean addToFavorites(int idOfCollege, Account account) {
		if(account.getFavorites()==null){
			account.setFavorites("");
		}
		if (account.getFavorites().length() < 60) {
			account.setFavorites(account.getFavorites() + String.valueOf(idOfCollege));
			System.out.println(account.getFavorites());
			updateAccount(account);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Update gpa.
	 *
	 * @param gpa the gpa
	 * @param account the account
	 * @return true, if successful
	 */
	public static boolean updateGpa(double gpa, Account account) {
		account.setGPA(gpa);
		updateAccount(account);
		return true;
	}

	/**
	 * Update zip.
	 *
	 * @param zip the zip
	 * @param account the account
	 * @return true, if successful
	 */
	public static boolean updateZip(int zip, Account account) {
		account.setZip(zip);
		updateAccount(account);
		return true;
	}

	/**
	 * Update sat scores.
	 *
	 * @param account the account
	 * @param sat the sat
	 * @return true, if successful
	 */
	public static boolean updateSatScores(Account account, double sat) {
		account.setSAT(sat);
		updateAccount(account);
		return true;
	}
	
	/**
	 * Update ACT scores.
	 *
	 * @param account the account
	 * @param ACT the act
	 * @return true, if successful
	 */
	public static boolean updateACTScores(Account account, double ACT){
		account.setACT(ACT);
		updateAccount(account);
		return true;
	}
	
	/**
	 * Update file.
	 *
	 * @param account the account
	 * @param file the file
	 * @return true, if successful
	 */
	public static boolean updateFile(Account account, byte[] file){
		account.setFile(file);
		updateAccount(account);
		return true;
	}
	
	/**
	 * Password satisfactory.
	 *
	 * @param password the password
	 * @return true, if successful
	 */
	public static boolean passwordSatisfactory(String password) {
		if (password.length() < 5) {
			return false;
		}
		String[] expres = new String[3];
		expres[0] = "[A-Z]";
		expres[1] = "[a-z]";
		expres[2] = "[0-9]";

		for (int i = 0; i < expres.length; i++) {
			Pattern pattern = Pattern.compile(expres[i]);
			java.util.regex.Matcher m = pattern.matcher(password);
			if (m.find() == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Gets the favorites.
	 *
	 * @param favorites the favorites
	 * @return the favorites
	 */
	public static int[] getFavorites(String favorites){
		int [] output= new int[favorites.length()/6];

		for(int i =0;i<favorites.length();i=i+6){
			output[i/6]=Integer.valueOf(favorites.substring(i,i+6));
		}

		return output;
	}

	/**
	 * Checks for favorited.
	 *
	 * @param favorites the favorites
	 * @param id the id
	 * @return true, if successful
	 */
	public static boolean hasFavorited(String favorites, int id){
		if(favorites==null){
			return false;
		}
		if(favorites.length()<5){
			System.out.println("5");
			return false;
		}
		int [] a = getFavorites(favorites);
		for(int i=0;i<a.length;i++){
			if(a[i]==id){
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the from favorites.
	 *
	 * @param favorites the favorites
	 * @param id the id
	 * @return the string
	 */
	public static String removeFromFavorites(String favorites, int id){
		String output="";
		int [] a = getFavorites(favorites);
		for(int i=0;i<a.length;i=i+6){
			if(a[i]!=id){
				output += favorites.substring(i,i+6);
			}

		}
		return output;
	}

	/**
	 * Gets the favorites bundle.
	 *
	 * @param favorites the favorites
	 * @return the favorites bundle
	 */
	public static CollegeBundle getFavoritesBundle(String favorites){
		CollegeBundle bundle = new CollegeBundle();
		int [] a = getFavorites(favorites);
		for(int i=0;i<a.length;i++){
			CollegeBundle temp = CollegeService.getCollegesToDisplay(a[i]);
			System.err.println(a.length);
			bundle.getList().add(temp.getList().get(0));//should only return one so not a problem
		}
		return bundle;
	}
}
