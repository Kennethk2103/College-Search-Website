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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("SignUp")
@Route(value = "SignUpView")
@AnonymousAllowed

public class SignUpView extends VerticalLayout{
   Account account;
    public SignUpView(){
         account= null;
        addClassName("SignUpView");
        Div mainDiv = new Div();
        H1 title = new H1("Sign up");
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
        Signinbtn.addClickListener(e ->Signinbtn.getUI().ifPresent(ui ->ui.navigate("LoginView")));
    
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
            Account check = AcountService.getAccountByEmail(emailField.getValue());

            if(check.getEmail()==null){
               account = new Account(emailField.getValue(), passwordField1.getValue());
               AcountService.AddAccount(account);
               account = AcountService.getAccountByEmail(emailField.getValue());
               System.out.println("Email " + account.getEmail());
               //MainView view = new MainView();
               //view.setAccount(account);
               //view.setAccount(account);
               WebBrowser browser = VaadinSession.getCurrent().getBrowser();
               System.out.println(browser.getAddress());
               UserHandler.getInstance().getData().addToMap(browser.getAddress(), account);       
               SignupBtn.getUI().ifPresent( ui -> ui.navigate(MainView.class, Integer.valueOf(account.getId())));
            }
            else{
               Notification.show("Account already exists with Email");
            }
      
      
      
          });
        h1.add(Signinbtn,SignupBtn);
        mainLayout.add(accountLayout,h1);
        mainDiv.add(mainLayout);
        mainDiv.addClassName("signUpDiv");
        NavBarView navBar = new NavBarView(account);
        add(navBar.getNav());
        add(mainDiv);
       
    
    
    
    
       }
}
