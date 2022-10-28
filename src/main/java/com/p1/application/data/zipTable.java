package com.p1.application.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Scanner;

import org.springframework.stereotype.Component;



public class zipTable implements Serializable{
    private static File zips = new File("zips.txt");
	private static Hashtable<Integer,Zip> table;
	
	public static Hashtable getTable() {
		return table;
	}

	public static void setTable(Hashtable tble) {
		if(tble==null) {
			table=new Hashtable(60000);
			String ziparr [];
			try {
				Scanner scan = new Scanner(zips);
				while(scan.hasNextLine()) {
					ziparr=scan.nextLine().split(",");
					Zip temp = new Zip(Integer.valueOf(ziparr[0]),Double.valueOf(ziparr[1]),Double.valueOf(ziparr[2]));
					table.put(temp.getZip(), temp);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			table = tble;
		}
	}
}
