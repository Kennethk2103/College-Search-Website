package com.p1.application.views;
import com.p1.application.service.AcountService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("SignUp")
@Route(value = "SignUpView")
public class SignUpView extends VerticalLayout{

    public SignUpView(){
        addClassName("SignUpView");
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setAlignItems(Alignment.CENTER);
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    
        TextField emailField = new TextField();
        emailField.setLabel("Email");
        emailField.setPattern("^(?=.*[@])(?=.*[.]).{5}.*$");
        emailField.setErrorMessage("Email Must be a Valid Email");


        PasswordField passwordField1 = new PasswordField();
        passwordField1.setLabel("Password");
        passwordField1.setHelperText("A password must be at least 8 characters. It has to have at least one upper case letter,one lower case and one digit.");
        passwordField1.setPattern("^(?=.*[0-9])(?=.*[a-zA-Z]).{8}.*$");
        passwordField1.setErrorMessage("Not a valid password");

        PasswordField passwordField2 = new PasswordField();
        passwordField2.setLabel("Confirm Password");
        passwordField2.setPattern("^(?=.*[0-9])(?=.*[a-zA-Z]).{8}.*$");
        passwordField2.setErrorMessage("Not a valid password");
        
        VerticalLayout accountLayout = new VerticalLayout(emailField,passwordField1,passwordField2);
        accountLayout.setAlignItems(Alignment.CENTER);
        accountLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    
        HorizontalLayout h1 = new HorizontalLayout();
        Button Signinbtn = new Button("Already have an Account?");     
        Signinbtn.addClickListener(e ->Signinbtn.getUI().ifPresent(ui ->ui.navigate("")));
    
        Button SignupBtn = new Button("Sign Up");
        SignupBtn.addClickListener(e ->{
  

            if(passwordField1.getValue().equals("")){
               Notification.show("Password must be inputted");
            }
            
            else if(emailField.getValue().equals("")){
               Notification.show("Email must be inputted");
            }
            else if(!AcountService.passwordSatisfactory(passwordField1.getValue())){
               Notification.show("Password dosent meet required specifications");
            }
            else if(!passwordField1.getValue().equals(passwordField2.getValue())){
                Notification.show("Passwords must be equal");
            }
      
      
            else{
               SignupBtn.getUI().ifPresent(ui ->ui.navigate("CatalogView"));
            }
      
      
      
          });
        h1.add(Signinbtn,SignupBtn);
        mainLayout.add(accountLayout,h1);
        add(mainLayout);
       
    
    
    
    
       }
}
