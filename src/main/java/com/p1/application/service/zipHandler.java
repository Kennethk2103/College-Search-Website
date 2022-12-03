package com.p1.application.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.p1.application.data.Zip;




public class zipHandler{
	
	private static File zips = new File("zips.txt");
	private  Hashtable<Integer,Zip> table;
	private static zipHandler singleton=null;
	public Hashtable getTable() {
		return table;
	}

	public Zip getZip(Integer i){
		return table.get(i);
	}

	public static zipHandler getHandler(){
		if(singleton==null){
			singleton = new zipHandler();
		}
		return singleton;
	}
	private zipHandler(){
		setTable();
	}

	private void setTable() {
		
			table=new Hashtable(60000);
			try {
				Scanner scan = new Scanner(zips);
				while(scan.hasNextLine()) {
					String [] ziparr=scan.nextLine().split(",");
					Zip temp = new Zip(Integer.valueOf(ziparr[0]),Double.valueOf(ziparr[1]),Double.valueOf(ziparr[2]));
					table.put(temp.getZip(), temp);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	public static double getDistance(int miles) {
		return (1 / 110.54) * miles;

	}

	

}
