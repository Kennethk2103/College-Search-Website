package com.p1.application.views;




import com.p1.application.data.Account;
import com.p1.application.data.College;
import com.p1.application.data.CollegeBundle;
import com.p1.application.service.AcountService;
import com.p1.application.service.CollegeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Catalog")
@Route(value = "CatalogView")
@AnonymousAllowed
///Main(Search through colleges and what not)
public class Catalog extends VerticalLayout {
    Div catalogDiv;
    Account account;
    int numberOfPages;

    public int getPages(){
        return numberOfPages;
    }
    public Div getCatalogDiv() {
        return catalogDiv;
    }


    public void setCatalogDiv(Div catalogDiv) {
        this.catalogDiv = catalogDiv;
    }

    
    public Catalog(CollegeBundle bundle, int start,int length, Account account) {
        catalogDiv=new Div();
        Div mDiv = catalogView(bundle,start,length, account);
        numberOfPages= (int) Math.ceil(Double.valueOf(bundle.getList().size())/Double.valueOf(length));
        add(mDiv);
        catalogDiv.add(mDiv);
        
    }


    public static Div catalogView(CollegeBundle bundle,int start,int length, Account account){
        Div mainDiv = new Div();
        int n;
        if((bundle.getList().size() - start-length)>=20){
            n=length+start;
        }
        else{
            n=bundle.getList().size();
        }
        for(int i=start;i<n;i++){
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
            div2.getUI().ifPresent(ui -> ui.navigate(CollegeView.class, Integer.valueOf(String.valueOf(CollegeService.getValueFor(college, "id")))));
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
           cardTitle.setText(String.valueOf(CollegeService.getValueFor(college, "id")));
           cardDiv.add(cardTitle);


           Paragraph cardText = new Paragraph();
           cardText.setText(String.valueOf(CollegeService.getValueFor(college, "school name")));

           cardDiv.add(cardText);

           
       
           mainDiv.add(div0);

    }
    return mainDiv;
    }

}
