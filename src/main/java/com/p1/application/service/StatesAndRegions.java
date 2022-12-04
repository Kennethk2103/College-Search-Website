package com.p1.application.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import com.p1.application.data.Regions;
import com.p1.application.data.Religions;
import com.p1.application.data.States;

public class StatesAndRegions {
    
    private static StatesAndRegions singleton =null;
    private LinkedList<States> StatesList;
    private LinkedList<Regions> RegionsList;
    private LinkedList<Religions> religionsList;

    public LinkedList<Religions> getReligionsList() {
        return religionsList;
    }


    public void setReligionsList(LinkedList<Religions> religionsList) {
        this.religionsList = religionsList;
    }


    private StatesAndRegions(){
        setUpRegions();
        setUpStates();
        setUpReligions();
    }


    public static StatesAndRegions getInstance(){
        if(singleton==null){
            singleton = new StatesAndRegions();
        }
        return singleton;
    }

    private void setUpStates(){
        File file = new File("statesFIPS.txt");
        StatesList = new LinkedList<>();
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String [] arr = line.split("\t");
                States temp = new States(Integer.valueOf(arr[0]),arr[1]);
                StatesList.add(temp);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    private void setUpRegions(){
        File file = new File("RegionId.txt");
        RegionsList = new LinkedList<>();
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String [] arr = line.split("\t");
                Regions temp = new Regions(Integer.valueOf(arr[0]),arr[1]);
                RegionsList.add(temp);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    private void setUpReligions(){
        File file = new File("religousAffliction.txt");
        religionsList = new LinkedList<>();
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String [] arr = line.split("\t");
                Religions temp = new Religions(Integer.valueOf(arr[0]),arr[1]);
                religionsList.add(temp);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    public LinkedList<States> getStatesList() {
        return StatesList;
    }


    public void setStatesList(LinkedList<States> statesList) {
        StatesList = statesList;
    }


    public LinkedList<Regions> getRegionsList() {
        return RegionsList;
    }


    public void setRegionsList(LinkedList<Regions> regionsList) {
        RegionsList = regionsList;
    }
    public States searchForState(String name){
        
            for(int i=0;i<StatesList.size();i++){
                if(StatesList.get(i).getName().equals(name)){
                    return StatesList.get(i);
                }
            }
            return null;
        
        
    }
    public Regions searchForRegion(String name){
        
        for(int i=0;i<RegionsList.size();i++){
            if(RegionsList.get(i).getName().equals(name)){
                return RegionsList.get(i);
            }
        }
        return null;
    
    }
    public Religions searchForReligion(int id){
        for(int i=0;i<religionsList.size();i++){
            if(religionsList.get(i).getId()==id){
                return religionsList.get(i);
            }
        }
        return null;
    }
    public Regions searchForRegionID(int id){
        
        for(int i=0;i<RegionsList.size();i++){
            if(RegionsList.get(i).getId()==id){
                return RegionsList.get(i);
            }
        }
        return null;
    
    }
    public States searchForStateID(int name){
        
        for(int i=0;i<StatesList.size();i++){
            if(StatesList.get(i).getId()==name){
                return StatesList.get(i);
            }
        }
        return null;
    
    
}
}

