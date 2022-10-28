package com.p1.application.views;

import com.p1.application.service.AcountService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route(value = "LoginView")
public class LoginView extends VerticalLayout{
    
    private  TextField emailField = new TextField();
    private PasswordField passwordField = new PasswordField();
    VerticalLayout mainLayout;


   public LoginView(){
    addClassName("LoginView");
    setSizeFull();
    
    mainLayout= new VerticalLayout();
    mainLayout.setAlignItems(Alignment.CENTER);
    mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
   
    H1 title = new H1("Log In");
    mainLayout.add(title);
    
    passwordField.setLabel("Password");
    passwordField.setHelperText("A password must be at least 8 characters. It has to have at least one upper case letter,one lower case and one digit.");
    passwordField.setPattern("^(?=.*[0-9])(?=.*[a-zA-Z]).{8}.*$");
    passwordField.setErrorMessage("Not a valid password");

    emailField.setLabel("Email");
    emailField.setPattern("^(?=.*[@])(?=.*[.]).{5}.*$");
    emailField.setErrorMessage("Email Must be a Valid Email");

    VerticalLayout accountLayout = new VerticalLayout();
    accountLayout.add(emailField,passwordField);
    accountLayout.setAlignItems(Alignment.CENTER);
    accountLayout.setJustifyContentMode(JustifyContentMode.CENTER);

    mainLayout.add(accountLayout);

    HorizontalLayout btnsLayout = new HorizontalLayout();
    Button SigninBtn = new Button("Login");     
    SigninBtn.addClickListener(e ->{
  

      if(passwordField.getValue().equals("")){
         Notification.show("Password must be inputted");
      }
      
      else if(emailField.getValue().equals(" ")){
         Notification.show("Email must be inputted");
      }
      else if(!AcountService.passwordSatisfactory(passwordField.getValue())){
         Notification.show("Password dosent meet required specifications");
      }


      else{
         SigninBtn.getUI().ifPresent(ui ->ui.navigate("CatalogView"));
      }



    });


    Button SignupBtn = new Button("Sign Up");
   SignupBtn.addClickListener(e ->
      SignupBtn.getUI().ifPresent(ui ->
         ui.navigate("SignUpView"))
   );

    btnsLayout.add(SigninBtn,SignupBtn);

    mainLayout.add(btnsLayout);
    
    add(mainLayout);


   }


 
}
