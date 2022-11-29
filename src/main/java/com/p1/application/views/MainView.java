package com.p1.application.views;

import java.util.LinkedList;

import com.p1.application.data.Account;
import com.p1.application.data.CollegeBundle;
import com.p1.application.service.AcountService;
import com.p1.application.service.CollegeService;
import com.p1.application.service.HtmlEditor;
import com.p1.application.service.UserHandler;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.server.auth.AnonymousAllowed;
@PageTitle("Main View")
@Route("")
@AnonymousAllowed
public class MainView extends VerticalLayout implements HasUrlParameter<Integer>{
    CollegeBundle bundle;
    Catalog cat;
    Div div0;
    Div catalogDiv;
    Account account;
    NavBarView navbar;
    int pageNum;
    public MainView(){
        pageNum=0;
        System.out.println("Account in main view " + account);
        div0= new Div();
        LinkedList<TextField> list= new LinkedList<>();
        bundle = CollegeService.startBundle();
       navbar = new NavBarView(account);

        NativeButton btn = new NativeButton("›");
        addClassName("MainView");
        String atr="class=\"btn searchBtn\" type=\"button\" data-bs-toggle=\"offcanvas\" data-bs-target=\"#offcanvasWithBothOptions\" aria-controls=\"offcanvasWithBothOptions\"";
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
        list.add(textField);
        

        HorizontalLayout HL2= new HorizontalLayout();
        TextField textField2 = new TextField();
        textField2.getElement().setAttribute("aria-label", "search");
        textField2.setPlaceholder("PlaceHolder");
        textField2.setClearButtonVisible(true);
        textField2.setPrefixComponent(VaadinIcon.SEARCH.create());
       
        HL2.add(textField2);
        v1.add(HL1);
        v1.add(HL2);
        
        HorizontalLayout HL3 =new HorizontalLayout();
        HL3.addClassName("CatalogAndSearch");
        cat = new Catalog(bundle,pageNum,20, account);
        catalogDiv = cat.getCatalogDiv();
        catalogDiv.addClassName("CatalogDiv");

        Button searchBtn = new Button("Search");
        Button backBtn = new Button("<");
        Button fowardBtn= new Button(">");
        v1.add(searchBtn);
        searchBtn.addClickListener( e->{
            if(!textField.getValue().equals("")){
                bundle = CollegeService.getCollegesToDisplay(list);
            }
            else{
                bundle = CollegeService.startBundle();
            }
            cat = new Catalog(bundle,pageNum,20, account);
            HL3.remove(catalogDiv);
            catalogDiv = cat.getCatalogDiv();
            catalogDiv.setClassName("CatalogDiv");
            if(cat.getPages()==0){
                fowardBtn.setEnabled(false);
                backBtn.setEnabled(false);
            }
            else{
                fowardBtn.setEnabled(true);
            }
            HL3.add(catalogDiv);
        });

        header.add(title);
        header.add(closeBtn);
        sideBar.add(header);
        sideBar.add(v1);
        HL3.add(btn,catalogDiv);
        div0.add(HL3);
        div0.addClassName("FullWidth");

        Div btnDiv = new Div();
        
        backBtn.setEnabled(false);
        btnDiv.add(backBtn);
        btnDiv.add(fowardBtn);
      
        

        fowardBtn.addClickListener(e->{
            pageNum = pageNum+1;
            if(pageNum!=0){
                backBtn.setEnabled(true);
            }
            cat = new Catalog(bundle,pageNum,20, account);
            HL3.remove(catalogDiv);
            catalogDiv = cat.getCatalogDiv();
            catalogDiv.setClassName("CatalogDiv");
            HL3.add(catalogDiv);
            if(pageNum == cat.getPages()){
                fowardBtn.setEnabled(false);
            }

        });
        backBtn.addClickListener(e->{
            pageNum--;
           
            cat = new Catalog(bundle,pageNum,20, account);
            HL3.remove(catalogDiv);
            catalogDiv = cat.getCatalogDiv();
            catalogDiv.setClassName("CatalogDiv");
            HL3.add(catalogDiv);
            if(pageNum==0){
                backBtn.setEnabled(false);
            }
            if(pageNum!=cat.getPages()){
                fowardBtn.setEnabled(true);
            }
        });





        div0.add(sideBar);
        div0.add(btnDiv);
        add(navbar.getNav());
        add(div0);
        

    }

  
    public Div getDiv(){
        return div0;
    }
    public Account getAccount(){
        return account;
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer parameter) {
        // TODO Auto-generated method stub
        if(parameter!=null){
            System.out.println("Parameters " + parameter);
            account = AcountService.getAccount((int)parameter);
            WebBrowser browser = VaadinSession.getCurrent().getBrowser();
            if(account.getEmail()!=null){
                if(UserHandler.getInstance().getData().getAccount(browser.getAddress()).getEmail().equals(account.getEmail())){
                    System.out.println("Account email : " + account.getEmail());
                    remove(navbar.getNav());
                    navbar = new NavBarView(account);
                    add(navbar.getNav());
                }
            }
        }
        else{
            WebBrowser browser = VaadinSession.getCurrent().getBrowser();
            account = UserHandler.getInstance().getData().getAccount(browser.getAddress());
            remove(navbar.getNav());
                    navbar = new NavBarView(account);
                    add(navbar.getNav());
        }


    }
    
    
    



}
