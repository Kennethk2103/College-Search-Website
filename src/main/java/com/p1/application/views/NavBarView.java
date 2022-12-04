package com.p1.application.views;


import com.p1.application.data.Account;
import com.p1.application.service.HtmlEditor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
@AnonymousAllowed

public class NavBarView extends VerticalLayout{
    Nav mainNav;
    Account account;
    public NavBarView(Account account){
        this.account=account;
        mainNav = new Nav();
        HtmlEditor.addAttribute(mainNav, "class=\"navbar bg-dark fixed-top\"");

        Div containerFluid = new Div();
        HtmlEditor.addAttribute(containerFluid, "class=\"container-fluid\"");
        mainNav.add(containerFluid);
        Div clickDiv = new Div();
        clickDiv.setClassName("linkToMain");
        clickDiv.addClickListener(e->{
            if(account==null){
                clickDiv.getUI().ifPresent(ui-> ui.navigate(MainView.class));
            }
            else{
                clickDiv.getUI().ifPresent(ui-> ui.navigate(MainView.class, account.getId()));
            }
        });
        Span anchor = new Span();
        anchor.add("CSearch");
        HtmlEditor.addAttribute(anchor, "class=\"navbar-brand mb-0 h1\"");
        clickDiv.add(anchor);
        containerFluid.add(clickDiv);

        NativeButton btn = new NativeButton();
        HtmlEditor.addAttribute(btn, "type=\"button\" class=\"btn btn-info\"");
        btn.add("Account");
        btn.addClickListener(e -> { 
            if(account==null){
                btn.getUI().ifPresent(ui -> ui.navigate("SignUpView"));
            }
            else{
                btn.getUI().ifPresent(ui -> ui.navigate(AccountView.class, account.getId()));
            }
        });
        containerFluid.add(btn);







    }
    public Nav getNav(){
        return mainNav;
    }
    public Account getAccount(){
        return account;
    }
	
	
}
