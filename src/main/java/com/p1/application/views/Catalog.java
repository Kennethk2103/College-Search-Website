package com.p1.application.views;


import java.util.LinkedList;
import java.util.Optional;

import com.p1.application.data.College;
import com.p1.application.data.CollegeBundle;
import com.p1.application.service.CollegeService;
import com.p1.application.service.CollegeSingleton;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;

@PageTitle("Catalog")
@Route(value = "CatalogView")
///Main(Search through colleges and what not)
public class Catalog extends VerticalLayout {
    Div catalogDiv;
    public Div getCatalogDiv() {
        return catalogDiv;
    }


    public void setCatalogDiv(Div catalogDiv) {
        this.catalogDiv = catalogDiv;
    }


    public Catalog(CollegeBundle bundle) {
        catalogDiv=new Div();
        Div mDiv = catalogView(bundle);

        add(mDiv);
        catalogDiv.add(mDiv);
        
    }


    public static Div catalogView(CollegeBundle bundle){
        Div mainDiv = new Div();
        int n;
        if(bundle.getList().size()>=20){
            n=20;
        }
        else{
            n=bundle.getList().size();
        }
        for(int i=0;i<n;i++){
            final int j=i;
            College college = bundle.getList().get(j);
            Div div0=new Div();
            div0.setClassName("BackgroundDiv");

           Div div1=new Div();
        
           div1.setClassName("card CollegeDisplayDiv");
           div0.add(div1);
           Div div2=new Div();
           div2.setClassName("row g-0 linkToCollege");


          div2.addClickListener(e -> {
            CollegeService.createCollegeView(college);

            div2.getUI().ifPresent(ui -> ui.navigate("CollegeInfo" +college.getId()));

        });
        
           div1.add(div2);
    
           Div imgDiv = new Div();
           imgDiv.setClassName("Col-md-4");
           div2.add(imgDiv);

           Div textDiv = new Div();
           textDiv.setClassName("col-md-8");
           div2.add(textDiv);

           
           Div cardDiv =new Div();
           cardDiv.setClassName("card-body CollegeDisplayDiv");
           textDiv.add(cardDiv);

           H5 cardTitle = new H5();
           cardTitle.setText(college.getName());
           cardDiv.add(cardTitle);


           Paragraph cardText = new Paragraph();
           cardText.setText(String.valueOf(college.getId()));

           cardDiv.add(cardText);

           mainDiv.add(div0);

    }
    return mainDiv;
    }
    

}
