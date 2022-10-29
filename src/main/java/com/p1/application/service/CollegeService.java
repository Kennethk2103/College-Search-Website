package com.p1.application.service;

import java.sql.Connection;
import java.util.LinkedList;
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

import com.p1.application.data.College;
import com.p1.application.data.CollegeBundle;
import com.p1.application.data.ConnectionJDBC;
import com.p1.application.views.CollegeView;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.RouteConfiguration;


@Service

public class CollegeService {

    public static CollegeBundle startBundle(){
        final CollegeBundle bundle = new CollegeBundle();
        Connection connection = null; 
        try{
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");
			
            PreparedStatement preState = connection.prepareStatement("SELECT * FROM Colleges");
   

            ResultSet rs = preState.executeQuery();

            while(rs.next()){
                College tempCollege= new College(rs.getInt("ID"), rs.getString("Name"),rs.getDouble("read25"),rs.getDouble("read75"),rs.getDouble("math25"),rs.getDouble("math75"));
                bundle.getList().add(tempCollege);

            }                            
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
        return bundle;
    }
    public static CollegeBundle getCollegesToDisplay(int number, String Name){
        final CollegeBundle bundle = new CollegeBundle();
        Connection connection = null; 
        try{
			connection = ConnectionJDBC.getConnection("MyDB.sqlite");
			
            PreparedStatement preState = connection.prepareStatement("SELECT * FROM Colleges WHERE ID LIKE ‘%" + Name +"%’");

            ResultSet rs = preState.executeQuery();

            while(rs.next()){
                College tempCollege= new College(rs.getInt("ID"), rs.getString("Name"),rs.getDouble("read25"),rs.getDouble("read75"),rs.getDouble("math25"),rs.getDouble("math75"));

                bundle.getList().add(tempCollege);

            }                            
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionJDBC.closeConnection(connection);
		}
        return bundle;

    }



    public static void createCollegeView(College college){
        if(RouteConfiguration.forSessionScope().isPathAvailable("CollegeInfo"+college.getId())){
            CollegeSingleton.getInstance().setCollege(college);
            return;
        }
        else{
            CollegeSingleton.getInstance().setCollege(college);
            CollegeView view = new CollegeView();
            RouteConfiguration.forSessionScope().setRoute("CollegeInfo" + college.getId(), view.getClass());

        }
      
    }


   
    
}
