package com.p1.application.views;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh;

import com.p1.application.data.College;
import com.p1.application.service.CollegeService;
import com.p1.application.service.CollegeSingleton;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;

@PageTitle("CollegeInfo")
public class CollegeView extends VerticalLayout{
    //template view for showing college information


    College college;
    
    public CollegeView(){
      College college =CollegeSingleton.getInstance().getCollege();
      final int id =college.getId();
      H1 collegeTitle = new H1(college.getName());
      collegeTitle.setClassName("Title");
      H2 lower25 = new H2("Lower 25 " +college.getLower());
      H2 upper = new H2("Upper 75 " + college.getUpper());

      Button backBtn = new Button("Back to Catalog");
      backBtn.addClickListener(e ->backBtn.getUI().ifPresent(ui ->ui.navigate("")));
      Div div0 = new Div();
  
      div0.add(lower25);
      div0.add(upper);
      div0.add(collegeTitle);
      div0.add(backBtn);
      add(div0);
    }


 






    



}
