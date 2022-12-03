package com.p1.application.views;

import com.p1.application.data.Account;
import com.p1.application.service.AcountService;
import com.p1.application.service.UserHandler;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Login")
@Route(value = "LoginView")
@AnonymousAllowed

public class LoginView extends VerticalLayout{
    
   Account account;
   public LoginView(){
        account= null;
       addClassName("SignUpView");
       Div mainDiv = new Div();
       H1 title = new H1("Login In");
       mainDiv.add(title);
       VerticalLayout mainLayout = new VerticalLayout();
       mainLayout.setSizeFull();
       mainLayout.setAlignItems(Alignment.CENTER);
       mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
   
       EmailField emailField = new EmailField();
       emailField.setLabel("Email address");
       emailField.getElement().setAttribute("name", "email");
       emailField.setErrorMessage("Enter a valid email address");
       emailField.setClearButtonVisible(true);



       PasswordField passwordField1 = new PasswordField();
       passwordField1.setLabel("Password");
       passwordField1.setHelperText("Input a password");


   
       
       VerticalLayout accountLayout = new VerticalLayout(emailField,passwordField1);
       accountLayout.setAlignItems(Alignment.CENTER);
       accountLayout.setJustifyContentMode(JustifyContentMode.CENTER);
   
       HorizontalLayout h1 = new HorizontalLayout();
       Button Signupbtn = new Button("Don't have an account?");     
       Signupbtn.addClickListener(e ->Signupbtn.getUI().ifPresent(ui ->ui.navigate("SignUpView")));
   
       Button SigninBtn = new Button("Sign in");
       SigninBtn.addClickListener(e ->{
          

           if(passwordField1.getValue().equals("")){
              Notification.show("Password must be inputted");
           }
           
           else if(emailField.getValue().equals("")){
              Notification.show("Email must be inputted");
           }
           System.out.println("Email field " + emailField.getValue());
           Account check = AcountService.getAccountByEmail(emailField.getValue());
           System.out.println(check.getPassword());
           if(check!=null){
              if(check.getPassword().equals(passwordField1.getValue())){
               WebBrowser browser = VaadinSession.getCurrent().getBrowser();
               UserHandler.getInstance().getData().addToMap(browser.getAddress(), check);  
               SigninBtn.getUI().ifPresent( ui -> ui.navigate(MainView.class, check.getId()));

              }
              else{
               Notification.show("Password is incorrect for email");
              }
           }
           else{
              Notification.show("Account doesent exist for email");
           }
     
     
     
         });
       h1.add(SigninBtn,Signupbtn);
       mainLayout.add(accountLayout,h1);
       mainDiv.add(mainLayout);
       mainDiv.addClassName("signUpDiv");
       NavBarView navBar = new NavBarView(account);
       add(navBar.getNav());
       add(mainDiv);
   }

 
}
