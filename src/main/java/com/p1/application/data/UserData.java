package com.p1.application.data;

import java.util.Hashtable;
import java.util.TreeMap;

public class UserData {
    private TreeMap<String,Account> tree;

    

    public UserData() {
		tree = new TreeMap<>();
	}

	public void addToMap(String ip, Account account){
        System.out.println("Added account " + account.getEmail());
        tree.put(ip, account);
    }

    public Account getAccount(String ip){
        return tree.get(ip);
    }

    public void removeFromMap(String ip){
        tree.remove(ip);
    }


}
