package com.p1.application.data;

import java.util.TreeMap;

/**
 * The Class UserData.
 */
public class UserData {
    
    /** The tree storing ip account pairs*/
    private TreeMap<String,Account> tree;

    

    /**
     * Instantiates a new user data map.
     */
    public UserData() {
		tree = new TreeMap<>();
	}

	/**
	 * Adds the to map.
	 *
	 * @param ip the ip
	 * @param account the account
	 */
	public void addToMap(String ip, Account account){
        System.out.println("Added account " + account.getEmail());
        tree.put(ip, account);
    }

    /**
     * Gets the account.
     *
     * @param ip the ip
     * @return the account
     */
    public Account getAccount(String ip){
        return tree.get(ip);
    }

    /**
     * Removes the from map.
     *
     * @param ip the ip
     */
    public void removeFromMap(String ip){
        tree.remove(ip);
    }


}
