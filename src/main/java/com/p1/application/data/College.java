package com.p1.application.data;

import java.util.Hashtable;
import java.util.LinkedList;
import org.springframework.boot.autoconfigure.domain.EntityScan;


/**
 * The Class College.
 */
@EntityScan
public class College {
    
    /** The map. */
    private Hashtable<String,LinkedList<Object>> map;
	
	/**
	 * Instantiates a new college.
	 * College data is stored inside of hashtable to enable factory to work
	 * @param list the list
	 */
	public College(LinkedList<LinkedList<Object>> list) {//0 is name 1 is type 2 is value
        map = new Hashtable<>((int)(list.size() * 1.5));
        for(int i =0;i<list.size();i++){
            String temp = (String) list.get(i).get(0);
            if(temp.contains("latest")){
                temp = temp.substring(temp.indexOf(".")+1,temp.length());
            }
            temp = temp.replace(".", "_");
            map.put(temp, list.get(i));
        }
	}
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Hashtable<String,LinkedList<Object>> getMap() {
		return map;
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the map
	 */
	public void setMap(Hashtable<String,LinkedList<Object>> map) {
		this.map = map;
	}
    
}
