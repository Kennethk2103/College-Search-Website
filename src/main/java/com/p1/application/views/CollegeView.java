package com.p1.application.views;

import java.util.LinkedList;

import com.p1.application.data.Account;
import com.p1.application.data.College;
import com.p1.application.data.GetSearchTerms;
import com.p1.application.service.AcountService;
import com.p1.application.service.CollegeService;
import com.p1.application.service.CollegeSingleton;
import com.p1.application.service.HtmlEditor;
import com.p1.application.service.UserHandler;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;

import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("CollegeInfo")
public class CollegeView extends VerticalLayout  {
  // template view for showing college information

  College college;
  Account account;

  public CollegeView() {
    createCollegeView();
  }

  public void createCollegeView() {
    College college = CollegeSingleton.getInstance().getCollege();
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
    int id = Integer.valueOf(String.valueOf(CollegeService.getValueFor(college, "id")));
    System.out.println(id);
    if (account != null) {
      System.out.println(AcountService.hasFavorited(account.getFavorites(), id));
      if (AcountService.hasFavorited(account.getFavorites(), id)) {
        btnDiv.add(unfavorite);
      } else {
        btnDiv.add(favorite);
      }

    }
    unfavorite.addClickListener(e -> {
      account.setFavorites(AcountService.removeFromFavorites(account.getFavorites(), id));
      AcountService.updateAccount(account);
      btnDiv.replace(unfavorite, favorite);
    });

    favorite.addClickListener(e -> {
      AcountService.addToFavorites(id, account);
      btnDiv.replace(favorite, unfavorite);
    });
    add(btnDiv);

    Div Accordion = new Div();
    HtmlEditor.addAttribute(Accordion, "class=\"accordion\" id=\"accordionExample\"");
    LinkedList<Div> DivList = new LinkedList<>();
    for (int i = 0; i < list.size(); i++) {

      if (list.get(i).get(0).length() < 5) {
        System.out.println("Smaller than 5");
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
            if (DivList.get(j).hasClassName(Devcat + "_accordion")) {
              Found = true;
              break;
            }
          }
          if (!Found) {
            DivList.add(new Div());
            DivList.get(DivPlacement++).addClassName(Devcat + "_accordion");
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
          String first = String.valueOf(name.replace("_", " ") + " : " + String.valueOf(findInfoList.get(2)));
          e1.appendChild(ElementFactory.createSpan(first));

        } else {
          Div accordionItem = new Div();
          System.out.println("Test2");

          accordionItem.addClassName("accordion-item");
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
          String first = String.valueOf(name.replace("_", " ") + " : " + String.valueOf(findInfoList.get(2)));
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

 

}
