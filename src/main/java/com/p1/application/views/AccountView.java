package com.p1.application.views;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.LinkedList;



import com.p1.application.data.Account;
import com.p1.application.data.CollegeBundle;
import com.p1.application.service.AcountService;
import com.p1.application.service.UserHandler;
import com.p1.application.service.zipHandler;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("UserDetails")
@PageTitle("UserDetails")
@AnonymousAllowed

public class AccountView extends HorizontalLayout implements HasUrlParameter<Integer> {
    Account account;
    NavBarView navbar;
    LinkedList<Files> list;
    byte [] file;
    public AccountView() {

    }

    public void makeView() {
        Div div0 = new Div();
        div0.addClassName("accountDiv");
        Button out = new Button("log out");
        out.addClickListener(e -> {
            WebBrowser browser = VaadinSession.getCurrent().getBrowser();
            UserHandler.getInstance().getData().removeFromMap(browser.getAddress());
            out.getUI().ifPresent(ui -> ui.navigate(MainView.class));
        });
        div0.add(out);
        HorizontalLayout h2 = new HorizontalLayout();
        h2.setAlignItems(Alignment.CENTER);
        h2.setJustifyContentMode(JustifyContentMode.CENTER);

        H2 titleForUser = new H2("Account Info");
        div0.add(titleForUser);
        Div emailDiv = new Div();
        emailDiv.addClassName("UserData");
        TextField email = new TextField("Email:");
        email.setReadOnly(true);
        email.setValue(account.getEmail());
        emailDiv.add(email);

        Div usernameDiv = new Div();
        usernameDiv.addClassName("UserData");
        TextField username = new TextField("username:");
        username.setReadOnly(true);
        username.setValue(account.getUsername());
        usernameDiv.add(username);

        Div zipDiv = new Div();
        zipDiv.addClassName("UserData");
        TextField zip = new TextField("zip:");
        if (account.getZip() != null && account.getZip() != 0) {
            zip.setValue(String.valueOf(account.getZip()));
        }
        zip.setPlaceholder("Input Zip");
        zipDiv.add(zip);

        h2.add(emailDiv, usernameDiv, zipDiv);
        add(h2);
        Div gpaDiv = new Div();
        gpaDiv.addClassName("UserData");
        TextField gpa = new TextField("gpa:");
        if (account.getGPA() != null) {
            gpa.setValue(String.valueOf(account.getGPA()));
        }
        gpa.setPlaceholder("Input gpa");
        gpaDiv.add(gpa);

        Div satDiv = new Div();
        satDiv.addClassName("UserData");
        TextField sat = new TextField("Sat:");
        if (account.getSAT() != null && account.getSAT() != 0) {
            sat.setValue(String.valueOf(account.getSAT()));
        }
        sat.setPlaceholder("Input sat");
        satDiv.add(sat);

        Div ACTDiv = new Div();
        ACTDiv.addClassName("UserData");
        TextField ACT = new TextField("ACT:");
        if (account.getACT() != null && account.getACT() != 0) {
            ACT.setValue(String.valueOf(account.getACT()));
        }
        ACT.setPlaceholder("Input ACT");
        ACTDiv.add(ACT);

        HorizontalLayout h1 = new HorizontalLayout();
        h1.setJustifyContentMode(JustifyContentMode.CENTER);
        h1.setAlignItems(Alignment.CENTER);
        h1.add(gpaDiv, satDiv, ACTDiv);
        div0.add(h2, h1);
        // file dump
        Div fileDump = new Div();
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload singleFileUpload = new Upload(memoryBuffer);
        singleFileUpload.setAcceptedFileTypes("application/pdf", ".pdf", "application/txt", ".txt");

        singleFileUpload.addSucceededListener(event -> {
            // Determine which file was uploaded

            // Get input stream specifically for the finished file
            InputStream fileData = memoryBuffer.getInputStream();
            try {
                file = fileData.readAllBytes();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        H3 txt = new H3("Insert essays ");
        fileDump.add(txt);
        fileDump.add(singleFileUpload);
        div0.add(fileDump);
        Button btn = new Button("Submit");
        btn.addClickListener(e -> {
            if (!zip.getValue().equals("") && !zip.getValue().equals(null) && Integer.valueOf(zip.getValue()) != 0) {
                if (zipHandler.getHandler().getTable().containsKey(Integer.valueOf(zip.getValue()))) {
                    AcountService.updateZip(Integer.valueOf(zip.getValue()), account);
                } else {
                    Notification.show("Zip is invalid");
                }
            }
            if (!gpa.getValue().equals("") && !gpa.getValue().equals(null) && Double.valueOf(gpa.getValue()) != 0.0) {
                if (Double.valueOf(gpa.getValue()) >= 0 && Double.valueOf(gpa.getValue()) <= 4) {
                    AcountService.updateGpa(Double.valueOf(gpa.getValue()), account);
                } else {
                    Notification.show("Gpa is invalid must be inbetween 4.0 and 0.0");
                }
            }
            if (!sat.getValue().equals("") && !sat.getValue().equals(null) && Double.valueOf(sat.getValue()) != 0.0) {
                if (Double.valueOf(sat.getValue()) >= 400 && Double.valueOf(gpa.getValue()) <= 1600) {
                    AcountService.updateSatScores(account, Double.valueOf(sat.getValue()));
                } else {
                    Notification.show("SAT is invalid must be inbetween 400 and 1600");
                }
            }
            if (!ACT.getValue().equals("") && !ACT.getValue().equals(null) && Double.valueOf(ACT.getValue()) != 0.0) {
                if (Double.valueOf(ACT.getValue()) >= 1 && Double.valueOf(ACT.getValue()) <= 36) {
                    AcountService.updateACTScores(account, Double.valueOf(ACT.getValue()));
                } else {
                    Notification.show("ACT is invalid must be inbetween 0 and 32");
                }
            }
            if(file!=null){
                AcountService.updateFile(account, file);
                Notification.show("Updated File");
            }

        });
        div0.add(btn);

        // add text field
        if (account.getFavorites() != null) {
            if (account.getFavorites().length() > 0) {
                CollegeBundle bundle = AcountService.getFavoritesBundle(account.getFavorites());
                Catalog cat = new Catalog(bundle, 0, 10, account);
                div0.add(cat.getCatalogDiv());
            }

        }
        add(div0);
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        account = AcountService.getAccount(parameter);
        System.out.println("Jail " + account.getEmail());
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        if (UserHandler.getInstance().getData().getAccount(browser.getAddress()) == null) {
            account = null;
            BabyJail();
        } else if (UserHandler.getInstance().getData().getAccount(browser.getAddress()).getEmail()
                .equals(account.getEmail())) {
            navbar = new NavBarView(account);
            add(navbar.getNav());
            makeView();
        } else {
            account = null;
            BabyJail();

        }
    }

    public void BabyJail() {
        Page page = UI.getCurrent().getPage();
        page.setLocation("https://www.youtube.com/embed/ReVHDWOL_pI?rel=0&autoplay=1");

    }
    @ClientCallable
    public void windowClosed() {
        if(account!=null){
            WebBrowser browser = VaadinSession.getCurrent().getBrowser();
            UserHandler.getInstance().getData().removeFromMap(browser.getAddress());
            System.out.println("Removed user");
        }
    }

}
