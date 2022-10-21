package com.p1.application.views;


import java.util.LinkedList;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Main")
@Route(value = "MainView")
///Main(Search through colleges and what not)
public class MainView extends VerticalLayout {



    public MainView() {
        for(int i=0;i<20;i++){
            final int j=i;
            Div genericDiv = new Div();
            genericDiv.setClassName("CollegeDisplayDiv");
            H1 nameOfCollege = new H1("166");
            genericDiv.add(nameOfCollege);
            H2 cityState = new H2("155");
            genericDiv.add(cityState);
            H3 dToSsISo = new H3("144");////Degree type(2,4 year) ownership(public or private) size of college and surrondings
            genericDiv.add(dToSsISo);
            H3 GradRate = new H3("133");
            genericDiv.add(GradRate);
            H3 cost = new H3("122");
            genericDiv.add(cost);
            H3 satRange = new H3("100");
            genericDiv.add(satRange);
            Button btn = new Button("Button test");
            btn.addClickListener(e->{
                Notification.show("" + String.valueOf(j));

            });

            btn.setClassName("btn btn-primary btn-lg");
            genericDiv.add(btn);


            add(genericDiv);
  
        }
        

    }
    

}
