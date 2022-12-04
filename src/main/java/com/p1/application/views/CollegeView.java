package com.p1.application.views;

import java.util.LinkedList;

import com.p1.application.data.Account;
import com.p1.application.data.College;
import com.p1.application.data.Religions;
import com.p1.application.service.AcountService;
import com.p1.application.service.CollegeService;
import com.p1.application.service.HtmlEditor;
import com.p1.application.service.StatesAndRegions;
import com.p1.application.service.UserHandler;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;

import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("CollegeInfo")
@Route("CollegeInfo")

public class CollegeView extends VerticalLayout implements HasUrlParameter<Integer> {
  // template view for showing college information

  College college;
  Account account;

  public CollegeView() {

  }

  public void createCollegeView() {

    LinkedList<LinkedList<String>> list = CollegeService.returnDataLibry();
    Div div0 = new Div();
    div0.setClassName("CollegeViewPane");
    WebBrowser browser = VaadinSession.getCurrent().getBrowser();
    Account account = UserHandler.getInstance().getData().getAccount(browser.getAddress());
    NavBarView navBar = new NavBarView(account);
    add(navBar.getNav());
    // Div headDiv = new Div();/// put all manual stuff here

    H1 collegeTitle = new H1(String.valueOf(CollegeService.getValueFor(college, "school	name")));
    collegeTitle.setClassName("Title");
    Div btnDiv = new Div();
    Button unfavorite = new Button("Unfavorite");
    Button favorite = new Button("Favorite");
    Button sendAblication = new Button("Send application");
    int id = Integer.valueOf(String.valueOf(CollegeService.getValueFor(college, "id")));
    if (account != null) {
      btnDiv.add(sendAblication);
      if (AcountService.hasFavorited(account.getFavorites(), id)) {
        btnDiv.add(unfavorite);
      } else {
        btnDiv.add(favorite);
      }

    }
    unfavorite.addClickListener(e -> {
      if(account!=null){
        account.setFavorites(AcountService.removeFromFavorites(account.getFavorites(), id));
        AcountService.updateAccount(account);
      }
      btnDiv.replace(unfavorite, favorite);
    });

    favorite.addClickListener(e -> {
      AcountService.addToFavorites(id, account);
      btnDiv.replace(favorite, unfavorite);
    });
    sendAblication.addClickListener(e->{
      Notification.show("Sent application");
    });
    add(btnDiv);
    Div Accordion = new Div();
    HtmlEditor.addAttribute(Accordion, "class=\"accordion\" id=\"accordionExample\"");
    LinkedList<Div> DivList = new LinkedList<>();
    for (int i = 0; i < list.size(); i++) {

      if (list.get(i).get(0).length() < 5) {
      } else if (list.get(i).get(0).contains("latest")) {
        int endOfLatest = list.get(i).get(0).indexOf(".");
        int endOfDev = list.get(i).get(0).indexOf(".", endOfLatest + 1);
        String Devcat = list.get(i).get(0).substring(endOfLatest + 1, endOfDev);
        int DivPlacement = 0;
        if (DivList.size() == 0) {
          DivList.add(new Div());
          DivList.get(0).addClassName(Devcat + "_accordion");
        } else {
          boolean Found = false;

          for (int j = 0; j < DivList.size(); j++) {
            DivPlacement = j;
            if (DivList.get(j).hasClassName(Devcat + "_accordion")) {// if found
              Found = true;
              break;
            }
          }
          if (!Found) {/// if div dosent exist for item
            Div tempDiv = new Div();
            tempDiv.addClassName(Devcat + "_accordion");
            DivPlacement++;
            DivList.add(tempDiv);
          }
        }
        if (DivList.get(DivPlacement).getChildren().count() > 0) {
          Element c1 = DivList.get(DivPlacement).getElement();
          while (c1.getChildren().count() < 2) {
            c1 = c1.getChild(0);
          }
          Element e1 = c1.getChild(1);
          e1 = e1.getChild(0);

          String name = list.get(i).get(0).substring(endOfLatest + 1, list.get(i).get(0).length()).replace(".", " ");
          LinkedList<Object> findInfoList = CollegeService.getList(college, name);
          String value = String.valueOf(findInfoList.get(2));
          //name.replace("_", " ") + " : " + value
          String first = formater(value, name.replace("_", " "));
          e1.appendChild(ElementFactory.createSpan(first));

        } else {
          Div accordionItem = new Div();

          accordionItem.addClassName("accordion-item");
          accordionItem.addClassName(Devcat);
          accordionItem.addClassName(String.valueOf(i));
          H2 tempH22 = new H2();
          HtmlEditor.addAttribute(tempH22, "\"accordion-header\" id=\"heading" + DivPlacement + "\"");
          NativeButton btn = new NativeButton();
          HtmlEditor.addAttribute(btn,
              "class=\"accordion-button\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collapse"
                  + DivPlacement + "\" aria-expanded=\"true\" aria-controls=\"collapse" + DivPlacement + "\"");
          btn.add(Devcat);
          tempH22.add(btn);
          accordionItem.add(tempH22);
          Div collapse = new Div();
          HtmlEditor.addAttribute(collapse,
              "id=\"collapse" + DivPlacement + "\" class=\"accordion-collapse collapse show\" aria-labelledby=\"heading"
                  + DivPlacement + "\" data-bs-parent=\"#accordionExample\"");
          Div accordionBody = new Div();
          accordionBody.addClassName("accordion-body");

          Span tempH2 = new Span();
          String name = list.get(i).get(0).substring(endOfLatest + 1, list.get(i).get(0).length()).replace(".", "_");
          System.out.println("Name " + name);
          LinkedList<Object> findInfoList = CollegeService.getList(college, name);
          String first = formater(String.valueOf(findInfoList.get(2)), name.replace("_", " "));
          tempH2.add(first);
          accordionBody.add(tempH2);
          collapse.add(accordionBody);
          accordionItem.add(collapse);
          DivList.get(DivPlacement).add(accordionItem);
        }
      }
    }
    for (int i = 0; i < DivList.size(); i++) {
      Accordion.add(DivList.get(i));
    }
    div0.add(collegeTitle);
    div0.add(btnDiv);
    div0.add(Accordion);
    add(div0);
  }

  @Override
  public void setParameter(BeforeEvent event, Integer parameter) {
    System.out.println("parameter" + parameter);
    if (!parameter.equals(null)) {
      college = CollegeService.getCollegeByID(parameter);
      if (college == null) {
        getUI().ifPresent(ui -> ui.navigate(MainView.class));
      }
      createCollegeView();

    } else {
      getUI().ifPresent(ui -> ui.navigate(MainView.class));
    }

  }

  private static String formater(String input, String name) {

    if(name.equals("school school url")){
      name = "school url";
      input = input.substring(0,3) + "." + input.substring(3, input.length()-3) + "." + input.substring(input.length()-3, input.length());
    }
    try{
      double val = Double.parseDouble(input);
      
      if(name.equals("school degrees awarded highest")){
        name = "school Highest awarded degree";
        if(val==0){
          input = "Dosent give out degrees";
        }
        else if(val==1){
          input = "Certificate degree";
        }
        else if(val==2){
          input = "Associate degree";
        }
        else if(val==3){
          input = "Bachelor's degree";
        }
        else if(val==4){
          input = "Graduate degree";
        }
        else{
          input = "not reported";
        }
        
      }
      else if(name.equals("school region id")){
        name = "school region";
        input = StatesAndRegions.getInstance().getRegionsList().get((int)val-1).getName();
      }
      else if(name.equals("school state fips")){
        name = "School State";
        input = StatesAndRegions.getInstance().getStatesList().get((int)val-1).getName();
      }
      else if(name.equals("school ownership")){
        if(val==1){
          input = "public";
        }
        else if(val == 2 || val==3){
          input = "private";
        }
        else{
          input = "unkown";
        }
      }
      else if(name.equals("school religious affiliation")){
        Religions rel = StatesAndRegions.getInstance().searchForReligion((int)val);
        if(rel==null){
          input = "Not Reported/Not religous";
        }
        else{
          input = rel.getName();
        }
      }
      else if(name.equals("admissions test requirements")){
        if(val==1){
          input = "Required";
        }
        else if(val==2){
          input = "Recomended";
        }
        else if(val == 3 || val ==5){
          input = "Not required";
        }
        else {
          input = "Not reported";
        }
      }
      else if(name.equals("completion title iv died by 2yrs") || name.equals("completion title iv died by 4yrs")){
        name = name.substring(0,name.indexOf(" ")+1) + "Percentage of people that died while enrolled in last " + name.substring(name.lastIndexOf(" "),name.length());
        int random = (int)(Math.random()*25);
        if(random==6){
          input = Math.random() * 100 + "";
          input = input.substring(0, 5) + "%";
        }
        else if(val<=0){
          input = "NA/Not reported";
        }
        else if(val <1 && val >0){
          input = val * 100 +"" ; 
          if(input.length()>5){
            input = input.substring(0, 5);
          }
          input += "%";
        }
      }
      else if(val <1 && val >0){
        input = val * 100 +"" ; 
        if(input.length()>5){
          input = input.substring(0, 5);
        }
        input += "%";
      }
      else if(val<=0){
          input = "NA/Not reported";
        
      }
      else if(val%1==0){
        input = (int)val + "";
      }
    }
    catch(NumberFormatException e){
     
    }
    name = name.substring(name.indexOf(" ")+1,name.length());
    return name + " : " + input;
  }

}
