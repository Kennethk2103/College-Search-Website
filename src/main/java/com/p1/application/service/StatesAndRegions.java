package com.p1.application.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import com.p1.application.data.Regions;
import com.p1.application.data.Religions;
import com.p1.application.data.States;

/**
 * The Class StatesAndRegions.
 */
public class StatesAndRegions {
    
    //need to be linked list to keep alphabetical order of lists
    /** The singleton. */
    //search is no bueno but is only 70 values to search max
    private static StatesAndRegions singleton =null;
    
    /** The States list. */
    private LinkedList<States> StatesList;
    
    /** The Regions list. */
    private LinkedList<Regions> RegionsList;
    
    /** The religions list. */
    private LinkedList<Religions> religionsList;

    /**
     * Gets the religions list.
     *
     * @return the religions list
     */
    public LinkedList<Religions> getReligionsList() {
        return religionsList;
    }


    /**
     * Sets the religions list.
     *
     * @param religionsList the new religions list
     */
    public void setReligionsList(LinkedList<Religions> religionsList) {
        this.religionsList = religionsList;
    }


    /**
     * Instantiates a new states and regions.
     */
    private StatesAndRegions(){///threading to help speed up setting up proccess
        Thread t1 = new Thread(()->{
            setUpRegions();
        });
        Thread t2 = new Thread(()->{
            setUpStates();
        });
        Thread t3 = new Thread(()->{
            setUpReligions();
        });

        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets the single instance of StatesAndRegions.
     *
     * @return single instance of StatesAndRegions
     */
    public static StatesAndRegions getInstance(){
        if(singleton==null){
            singleton = new StatesAndRegions();
        }
        return singleton;
    }

    /**
     * Sets the up states.
     */
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
    
    /**
     * Sets the up regions.
     */
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
    
    /**
     * Sets the up religions.
     */
    private void setUpReligions(){
        File file = new File("ReligionsFile.txt");
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

    /**
     * Gets the states list.
     *
     * @return the states list
     */
    public LinkedList<States> getStatesList() {
        return StatesList;
    }


    /**
     * Sets the states list.
     *
     * @param statesList the new states list
     */
    public void setStatesList(LinkedList<States> statesList) {
        StatesList = statesList;
    }


    /**
     * Gets the regions list.
     *
     * @return the regions list
     */
    public LinkedList<Regions> getRegionsList() {
        return RegionsList;
    }


    /**
     * Sets the regions list.
     *
     * @param regionsList the new regions list
     */
    public void setRegionsList(LinkedList<Regions> regionsList) {
        RegionsList = regionsList;
    }
    
    /**
     * Search for state.
     *
     * @param name the name
     * @return the states
     */
    public States searchForState(String name){
        
            for(int i=0;i<StatesList.size();i++){
                if(StatesList.get(i).getName().equals(name)){
                    return StatesList.get(i);
                }
            }
            return null;
        
        
    }
    
    /**
     * Search for region.
     *
     * @param name the name
     * @return the regions
     */
    public Regions searchForRegion(String name){
        
        for(int i=0;i<RegionsList.size();i++){
            if(RegionsList.get(i).getName().equals(name)){
                return RegionsList.get(i);
            }
        }
        return null;
    
    }
    
    /**
     * Search for religion.
     *
     * @param id the id
     * @return the religions
     */
    public Religions searchForReligion(int id){
        for(int i=0;i<religionsList.size();i++){
            if(religionsList.get(i).getId()==id){
                return religionsList.get(i);
            }
        }
        return null;
    }
    
    /**
     * Search for region ID.
     *
     * @param id the id
     * @return the regions
     */
    public Regions searchForRegionID(int id){
        
        for(int i=0;i<RegionsList.size();i++){
            if(RegionsList.get(i).getId()==id){
                return RegionsList.get(i);
            }
        }
        return null;
    
    }
    
    /**
     * Search for state ID.
     *
     * @param name the name
     * @return the states
     */
    public States searchForStateID(int name){
        
        for(int i=0;i<StatesList.size();i++){
            if(StatesList.get(i).getId()==name){
                return StatesList.get(i);
            }
        }
        return null;
    
    
}
}

