package com.p1.application.views;


import com.p1.application.data.Account;
import com.p1.application.data.CollegeBundle;
import com.p1.application.data.Regions;
import com.p1.application.data.States;
import com.p1.application.service.AcountService;
import com.p1.application.service.CollegeService;
import com.p1.application.service.HtmlEditor;
import com.p1.application.service.StatesAndRegions;
import com.p1.application.service.UserHandler;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
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
        UI.getCurrent().getPage().executeJs("function closeListener() { $0.$server.windowClosed(); } " +
        "window.addEventListener('beforeunload', closeListener); " +
        "window.addEventListener('unload', closeListener);",getElement());
        pageNum=0;
        div0= new Div();
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
        
        //H1 title = new H1("Search By");
        //atr="class=\"offcanvas-title\" id=\"offcanvasWithBothOptionsLabel\"";
        //HtmlEditor.addAttribute(title, atr);

        NativeButton closeBtn = new NativeButton();
        atr = "type=\"button\" class=\"btn-close\" data-bs-dismiss=\"offcanvas\" aria-label=\"Close\"";
        HtmlEditor.addAttribute(closeBtn, atr);
        

        VerticalLayout v1= new VerticalLayout();
        HorizontalLayout HL1= new HorizontalLayout();
        Button searchBtn = new Button("Search");
        TextField textField1 = new TextField();
        textField1.getElement().setAttribute("aria-label", "search");
        textField1.setPlaceholder("Enter College Name");
        textField1.setClearButtonVisible(true);
        textField1.setPrefixComponent(VaadinIcon.SEARCH.create());
        HL1.add(textField1);
        HL1.add(searchBtn);
        
        
        v1.add(HL1);
        ComboBox<States> comboBoxState = new ComboBox<>("State");
        comboBoxState.setItems(StatesAndRegions.getInstance().getStatesList());
        comboBoxState.setItemLabelGenerator(States::getName);
        v1.add(comboBoxState);

        ComboBox<Regions> comboBoxRegion = new ComboBox<>("Region");
        comboBoxRegion.setItems(StatesAndRegions.getInstance().getRegionsList());
        comboBoxRegion.setItemLabelGenerator(Regions::getName);
        v1.add(comboBoxRegion);
       
        HorizontalLayout zips = new HorizontalLayout();
        IntegerField ZipField = new IntegerField();
        ZipField.setLabel("Enter Zip");
        ZipField.setMax(5);
        ZipField.setMin(5);
        zips.add(ZipField);
        IntegerField DistanceField = new IntegerField();
        DistanceField.setLabel("Enter distance from zip");
        zips.add(DistanceField);
        v1.add(zips);
        
        HorizontalLayout cost = new HorizontalLayout();
        IntegerField CostField = new IntegerField();
        CostField.setLabel("Enter Cost");
        cost.add(CostField);
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setItems("In state","Out of state");
        radioGroup.setValue("In state");
        cost.add(radioGroup);
        v1.add(cost);

        HorizontalLayout sact = new HorizontalLayout();
        IntegerField SatField = new IntegerField();
        SatField.setLabel("Enter Sat");
        sact.add(SatField);

        IntegerField ActField = new IntegerField();
        ActField.setLabel("Enter ACT");
        sact.add(ActField);
        v1.add(sact);

        HorizontalLayout checkH = new HorizontalLayout();
        RadioButtonGroup<String> compradioGroup2 = new RadioButtonGroup<>();
        compradioGroup2.setLabel("Degree Level");
        compradioGroup2.setItems("2 Year","4 Year","Graduate");
        compradioGroup2.setValue("4 Year");
        RadioButtonGroup<String> compradioGroup = new RadioButtonGroup<>();
        compradioGroup.setLabel("Has Comp Cci");
        compradioGroup.setItems("Yes","Don't care");
        compradioGroup.setValue("Don't care");
        checkH.add(compradioGroup2);
        checkH.add(compradioGroup);
       
        v1.add(checkH);
   

        

        
        HorizontalLayout HL3 =new HorizontalLayout();
        HL3.addClassName("CatalogAndSearch");
        cat = new Catalog(bundle,pageNum,20, account);
        catalogDiv = cat.getCatalogDiv();
        catalogDiv.addClassName("CatalogDiv");

        Button backBtn = new Button("<");
        Button fowardBtn= new Button(">");
        searchBtn.addClickListener( e->{
            bundle = CollegeService.getCollegesToDisplay(textField1.getValue(),comboBoxState.getValue(),comboBoxRegion.getValue(), ZipField.getValue(),DistanceField.getValue(),SatField.getValue(),ActField.getValue(), CostField.getValue(), radioGroup.getValue(), compradioGroup2.getValue(), compradioGroup.getValue());
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

        //header.add(title);
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
        if(parameter!=null){
            account = AcountService.getAccount((int)parameter);
            WebBrowser browser = VaadinSession.getCurrent().getBrowser();
            if(account.getEmail()!=null){
                if(UserHandler.getInstance().getData().getAccount(browser.getAddress()).getEmail().equals(account.getEmail())){
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
    @ClientCallable
    public void windowClosed() {
        if(account!=null){
            WebBrowser browser = VaadinSession.getCurrent().getBrowser();
            UserHandler.getInstance().getData().removeFromMap(browser.getAddress());
          }
    }


}
