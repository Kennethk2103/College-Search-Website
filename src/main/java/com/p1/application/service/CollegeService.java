package com.p1.application.service;

import java.sql.Connection;
import java.util.LinkedList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
import com.p1.application.data.College;
import com.p1.application.data.CollegeBundle;
import com.p1.application.data.ConnectionJDBC;
import com.p1.application.data.GetSearchTerms;
import com.p1.application.data.Regions;
import com.p1.application.data.States;
import com.p1.application.data.Zip;



/**
 * The Class CollegeService.
 */
@Service

public class CollegeService {
    
    /** The list. */
    static LinkedList<LinkedList<String>> list = GetSearchTerms.getTerms();
    
    /**
     * Gets the list.
     *
     * @param college the college
     * @param name the name
     * @return the list
     */
    public static LinkedList<Object> getList(College college,String name){
        name=name.replace(".", "_");
        name=name.replace("\t", " ");
        name=name.replace(" ", "_");
        if(college.getMap().get(name)!=null){

            return (LinkedList<Object>) college.getMap().get(name.toLowerCase());
        }
        else{
            return null;
        }
    }

    /**
     * Gets the data type for.
     *
     * @param college the college
     * @param name Nameo of field
     * @return the data type
     */
    public static String getDataTypeFor(College college,String name){
        LinkedList<Object> list = getList(college, name.toLowerCase());
        if(list!=null){
            return (String) list.get(1);
        }
        else{
            return null;
        }
    }
    
    /**
     * Gets the value for a field.
     *
     * @param college the college
     * @param name of field
     * @return the value of that field
     */
    public static Object getValueFor(College college, String name){ 

        LinkedList<Object> list = getList(college,name.toLowerCase());
        return list.get(2);
    }

    /**
     * Creates college bundle on program start.
     *
     * @return the college bundle
     */
    public static CollegeBundle startBundle(){
        final CollegeBundle bundle = new CollegeBundle();
        Connection connection = null; 
        try{
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");
			
            PreparedStatement preState = connection.prepareStatement("SELECT * FROM Colleges");
   
            ResultSet rs = preState.executeQuery();

            while(rs.next()){
                
                College tempCollege= rsToCollege(rs);
                bundle.getList().add(tempCollege);
            }                            
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
        return bundle;
    }

    /**
     * Result set to college.
     *
     * @param rs the Result set of college
     * @return the college
     */
    public static College rsToCollege(ResultSet rs){
        LinkedList<LinkedList<Object>> list1 = new LinkedList<>();
        try{
            for(int i=0;i<list.size();i++){
                list1.add(new LinkedList<Object>());
                list1.get(i).add(list.get(i).get(0));
                list1.get(i).add(list.get(i).get(1));
                String thing = list.get(i).get(0).replace(".", "_");
                if(list.get(i).get(1).toLowerCase().equals("integer")){
                    list1.get(i).add(rs.getInt(thing));
                }
                else if(list.get(i).get(1).toLowerCase().contains("string") || list.get(i).get(1).toLowerCase().contains("autocomplete")){
                    list1.get(i).add(rs.getString(thing));
                }
                else if(list.get(i).get(1).toLowerCase().contains("float")){
                    list1.get(i).add(rs.getFloat(thing));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return new College(list1);
    }


    /**
     * Return data libry.
     *
     * @return the linked list
     */
    public static LinkedList<LinkedList<String>> returnDataLibry(){
        return list;
    }

    /**
     * Sort data libry.
     *
     * @return the linked list
     */
    public static LinkedList<LinkedList<String>> sortDataLibry(){/// first linkedlist divides by devcat, second holds by dev name, third holds data type
        LinkedList<LinkedList<String>> librySorted = new LinkedList<>();
        for(int i =0;i<list.size();i++){
            if(list.get(i).get(0).contains("latest")){
                librySorted.add(list.get(i));
            }

        }
        return librySorted;
        

    }


    /**
     * Gets the college by ID.
     *
     * @param id the id
     * @return the college with id
     */
    public static College getCollegeByID(int id){
        Connection connection = null; 
        try{
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");
			String statment1="SELECT * FROM Colleges WHERE ID = " + id;
            PreparedStatement preState = connection.prepareStatement(statment1);
            ResultSet rs = preState.executeQuery();
            return rsToCollege(rs);
  
        }
                                     
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
        return null;
        

    }

    /**
     * Gets the colleges to display.
     *
     * @param name the name
     * @param state the state
     * @param region the region
     * @param zipI the zip
     * @param Distance the distance
     * @param Sat the sat
     * @param Act the act
     * @param cost the cost
     * @param instate the instate
     * @param degreeLevel the degree level
     * @param hasCompSci the percentage of comp sci majors at college
     * @return the colleges to display
     */
    public static CollegeBundle getCollegesToDisplay(String name, States state, Regions region, Integer zipI,
    Integer Distance, Integer Sat, Integer Act,Integer cost, String instate, String degreeLevel, String hasCompSci){
        final CollegeBundle bundle = new CollegeBundle();
        Connection connection = null; 
        try{
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");
            int i =0;
			String statment1="SELECT * FROM Colleges ";
            if(name!=null){
                if(!name.replace(" ", "").equals("")){
                    statment1 +=" WHERE latest_school_name LIKE '%" + name+ "%'";
                    i++;
                }
            }
            if(state!=null){
                
                if(i>0){
                        statment1 += " AND";
                }
                else{
                    statment1 += " WHERE";
                }
                    i++;
                    statment1 += " latest_school_state_fips = " + state.getId() + "";
                
            }
            if(region!=null){
                    if(i>0){
                        statment1 += " AND";
                    }
                    else{
                        statment1 += " WHERE";
                    }
                    i++;
                    statment1 += " latest_school_region_id = " + region.getId() + "";
                
            }
            if(Sat!=null){
                if(i>0){
                    statment1 += " AND";
                }
                else{
                    statment1 += " WHERE";
                }
                i++;
                statment1 += "  latest_admissions_sat_scores_25th_percentile_math <= " + Sat/2 + "";
            }
            if(Act!=null){
                if(i>0){
                    statment1 += " AND";
                }
                else{
                    statment1 += " WHERE";
                }
                i++;
                statment1 += " latest_admissions_act_scores_25th_percentile_cumulative <= " + Act + "";
            }
            if(cost!=null){
                if(i>0){
                    statment1 += " AND";
                }
                else{
                    statment1 += " WHERE";
                }
                if(instate.equals("In state")){
                    i++;

                    statment1 += " latest_cost_tuition_in_state <= " + cost + "";
                }
                else{
                    i++;
                    statment1 += " latest_cost_tuition_out_of_state <= " + cost + "";
                }
            }
            if(degreeLevel!=null){
                if(i>0){
                    statment1 += " AND";
                }
                else{
                    statment1 += " WHERE";
                }
                if(degreeLevel.equals("Graduate")){
                    i++;

                    statment1 += " latest_school_degrees_awarded_highest = 4";
                }
                else if(degreeLevel.equals("4 Year")){
                    i++;
                    statment1 += " latest_school_degrees_awarded_highest >= 3";
                }
                else {
                    i++;
                    statment1 += " latest_school_degrees_awarded_highest >= 2";
                }
               
               
            }
            if(hasCompSci!=null){
                if(hasCompSci.equals("Yes")){
                    if(i>0){
                        statment1 += " AND";
                    }
                    else{
                        statment1 += " WHERE";
                    }
                    statment1 += " latest_academics_program_percentage_computer >0";
                    i++;

                }
            }
            if(Distance!=null && zipI!=null){
            	Zip zip = zipHandler.getHandler().getZip(zipI);
            	if(zip!=null) {
            		 if(i>0){
                         statment1 += " AND";
                     }
                     else{
                         statment1 += " WHERE";
                     }
                     i++;
                     
                     double distance = zipHandler.getDistance(Distance);
                     double distance1 = zip.getLatitude()-distance;
                     double distance2 = zip.getLatitude()+distance;
                     double distance3 = zip.getLongitude()-distance;
                     double distance4 = zip.getLongitude()+distance;

                     if(distance1>distance2){
                         statment1 += " location_lat > " + distance4 + " AND location_lat<"+ distance3;
                     }
                     else{
                         statment1 += " location_lat > " + distance3 + " AND location_lat<"+ distance4;
                     }
                     if(distance3>distance4){
                         statment1 += " AND location_lon > " + distance2 + " AND location_lon<"+ distance1;
                     }
                     else{
                         statment1 += " AND location_lon > " + distance1 + " AND location_lon<"+ distance2;
                     }
            	}
               

            }

            System.out.println(statment1);
            if(i==0){
                return startBundle();
            }
            else{
                PreparedStatement preState = connection.prepareStatement(statment1);

                ResultSet rs = preState.executeQuery();
                while(rs.next()){
                    College tempCollege= rsToCollege(rs);
                    
                    
                    bundle.getList().add(tempCollege);
                    
                }   
            }
            
                                     
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
        return bundle;

    }
    
    /**
     * Gets the colleges to display.
     *
     * @param a the ID
     * @return the colleges to display
     */
    public static CollegeBundle getCollegesToDisplay(int a){
        Connection connection = null; 
        CollegeBundle bundle;
        try{
            bundle=new CollegeBundle();
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");
			String statment1="SELECT * FROM Colleges WHERE ID LIKE '" + a + "'";
            PreparedStatement preState = connection.prepareStatement(statment1);

            ResultSet rs = preState.executeQuery();
            while(rs.next()){
                College tempCollege= rsToCollege(rs);
                bundle.getList().add(tempCollege);
            }  
            return bundle;                          
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
        return null;

    }

    
}
