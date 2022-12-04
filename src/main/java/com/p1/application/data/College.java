package com.p1.application.data;

import java.util.HashMap;
import java.util.LinkedList;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan
public class College {
    
    private HashMap<String,LinkedList<Object>> map;
	public College(LinkedList<LinkedList<Object>> list) {//0 is name 1 is type 2 is value
        map = new HashMap<>((int)(list.size() * 1.5));
        for(int i =0;i<list.size();i++){
            String temp = (String) list.get(i).get(0);
            if(temp.contains("latest")){
                temp = temp.substring(temp.indexOf(".")+1,temp.length());
            }
            temp = temp.replace(".", "_");
            map.put(temp, list.get(i));
        }
	}
	public HashMap<String,LinkedList<Object>> getMap() {
		return map;
	}
	public void setMap(HashMap<String,LinkedList<Object>> map) {
		this.map = map;
	}
    
}
