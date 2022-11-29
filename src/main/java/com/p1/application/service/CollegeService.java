package com.p1.application.service;

import java.sql.Connection;
import java.util.LinkedList;

import javax.sound.midi.SysexMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p1.application.data.Account;
import com.p1.application.data.College;
import com.p1.application.data.CollegeBundle;
import com.p1.application.data.ConnectionJDBC;
import com.p1.application.data.GetSearchTerms;
import com.p1.application.views.CollegeView;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.RouteConfiguration;


@Service

public class CollegeService {
    static LinkedList<LinkedList<String>> list = GetSearchTerms.getTerms();
    
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

    public static String getDataTypeFor(College college,String name){
        LinkedList<Object> list = getList(college, name.toLowerCase());
        if(list!=null){
            return (String) list.get(1);
        }
        else{
            return null;
        }
    }
    public static Object getValueFor(College college, String name){ 

        LinkedList<Object> list = getList(college,name.toLowerCase());
        return list.get(2);
    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new College(list1);
    }


    public static LinkedList<LinkedList<String>> returnDataLibry(){
        return list;
    }

    public static LinkedList<LinkedList<String>> sortDataLibry(){/// first linkedlist divides by devcat, second holds by dev name, third holds data type
        LinkedList<LinkedList<String>> librySorted = new LinkedList<>();
        for(int i =0;i<list.size();i++){
            if(list.get(i).get(0).contains("latest")){
                librySorted.add(list.get(i));
            }

        }
        return librySorted;
        

    }


    

    public static CollegeBundle getCollegesToDisplay(LinkedList<TextField> list2){
        final CollegeBundle bundle = new CollegeBundle();
        Connection connection = null; 
        try{
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");
			String statment1="SELECT * FROM Colleges WHERE latest_school_name LIKE '%" + list2.get(0).getValue()+ "%'";
            PreparedStatement preState = connection.prepareStatement(statment1);

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



    public static void createCollegeView(College college){
        if(RouteConfiguration.forSessionScope().isPathAvailable("CollegeInfo"+getValueFor(college, "ID"))){
            CollegeSingleton.getInstance().setCollege(college);
            return;
        }
        else{
            CollegeSingleton.getInstance().setCollege(college);
            CollegeView view = new CollegeView();
            RouteConfiguration.forSessionScope().setRoute("CollegeInfo" + getValueFor(college, "ID"), view.getClass());
        }
      
    }


   
    
}
