package com.p1.application.views;

import org.apache.commons.text.StringEscapeUtils;
import org.w3c.dom.Text;

import com.p1.application.data.CollegeBundle;
import com.p1.application.service.CollegeService;
import com.p1.application.service.HtmlEditor;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
@PageTitle("Main View")
@Route ("")
public class MainView extends VerticalLayout{
    Div div0;
    CollegeBundle bundle;
    Catalog cat;
    Div catalogDiv;
    public MainView(){
        bundle = CollegeService.startBundle();

        NativeButton btn = new NativeButton("Search tab");
        String atr="class=\"btn btn-primary\" type=\"button\" data-bs-toggle=\"offcanvas\" data-bs-target=\"#offcanvasWithBothOptions\" aria-controls=\"offcanvasWithBothOptions\"";
        HtmlEditor.addAttribute(btn, atr);

        Div sideBar = new Div();
        atr = "class=\"offcanvas offcanvas-start\" data-bs-scroll=\"true\" tabindex=\"-1\" id=\"offcanvasWithBothOptions\" aria-labelledby=\"offcanvasWithBothOptionsLabel\"";
        HtmlEditor.addAttribute(sideBar,atr);
        
        Div header = new Div();
        atr = "class=\"offcanvas-header\"";
        HtmlEditor.addAttribute(header, atr);
        sideBar.add(header);
        
        H1 title = new H1("Title");
        atr="class=\"offcanvas-title\" id=\"offcanvasWithBothOptionsLabel\"";
        HtmlEditor.addAttribute(title, atr);

        NativeButton closeBtn = new NativeButton();
        atr = "type=\"button\" class=\"btn-close\" data-bs-dismiss=\"offcanvas\" aria-label=\"Close\"";
        HtmlEditor.addAttribute(closeBtn, atr);
        

        VerticalLayout v1= new VerticalLayout();
        HorizontalLayout HL1= new HorizontalLayout();
        TextField textField = new TextField();
        textField.getElement().setAttribute("aria-label", "search");
        textField.setPlaceholder("Enter College Name");
        textField.setClearButtonVisible(true);
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        HL1.add(textField);
        HorizontalLayout HL2= new HorizontalLayout();
        TextField textField2 = new TextField();
        textField2.getElement().setAttribute("aria-label", "search");
        textField2.setPlaceholder("Enter Sat");
        textField2.setClearButtonVisible(true);
        textField2.setPrefixComponent(VaadinIcon.SEARCH.create());
       
        HL2.add(textField2);
        v1.add(HL1);
        v1.add(HL2);


        cat = new Catalog(bundle);
        catalogDiv = cat.getCatalogDiv();
        catalogDiv.addClassName("CatalogDiv");

        Button searchBtn = new Button("Search");
        v1.add(searchBtn);
        searchBtn.addClickListener( e->{
            if(!textField.getValue().equals("")){
                bundle = CollegeService.getCollegesToDisplay(20, textField.getValue());
            }
            else{
                bundle = CollegeService.startBundle();
            }
            cat = new Catalog(bundle);
            remove(catalogDiv);
            catalogDiv = cat.getCatalogDiv();
            add(catalogDiv);
        });

        header.add(title);
        header.add(closeBtn);
        sideBar.add(header);
        sideBar.add(v1);
        add(btn);
        add(sideBar);

        add(catalogDiv);





    }

}
