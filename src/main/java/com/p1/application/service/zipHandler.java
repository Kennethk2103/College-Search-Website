
package com.p1.application.service;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import com.p1.application.data.Zip;

/**
 * The Class zipHandler.
 */
public class zipHandler{
	
	/** The File holding the zips. */
	private static File zips = new File("zips.txt");
	
	/** The table holding zips. */
	private  Hashtable<Integer,Zip> table;
	
	/** The singleton. */
	private static zipHandler singleton=null;
	
	/**
	 * Gets the table containing zips.
	 *
	 * @return the table
	 */
	public Hashtable<Integer,Zip> getTable() {
		return table;
	}

	/**
	 * Gets the zip object for a specified zip code.
	 *
	 * @param i the i
	 * @return the zip object 
	 */
	public Zip getZip(Integer i){
		return table.get(i);
	}

	/**
	 * Gets the handler.
	 *
	 * @return the handler
	 */
	public static zipHandler getHandler(){
		if(singleton==null){
			singleton = new zipHandler();
		}
		return singleton;
	}
	
	/**
	 * Instantiates a new zip handler.
	 */
	private zipHandler(){
		setTable();
	}

	/**
	 * Sets the table.
	 */
	private void setTable() {
			table=new Hashtable<Integer,Zip>(60000);
			try {
				Scanner scan = new Scanner(zips);
				while(scan.hasNextLine()) {
					String [] ziparr=scan.nextLine().split(",");
					Zip temp = new Zip(Integer.valueOf(ziparr[0]),Double.valueOf(ziparr[1]),Double.valueOf(ziparr[2]));
					table.put(temp.getZip(), temp);
				}
				scan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		
	}
	
	/**
	 * Gets the distance.
	 *
	 * @param miles the miles
	 * @return the distance in degrees long/lat
	 */
	public static double getDistance(int miles) {
		return (1 / 110.54) * miles;

	}

	

}
