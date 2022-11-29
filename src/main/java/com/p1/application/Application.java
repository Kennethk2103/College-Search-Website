package com.p1.application;

import com.p1.application.data.AccountSQL;
import com.p1.application.data.CollegeSQL;
import com.p1.application.data.GetSearchTerms;
import com.p1.application.data.Jsonpasser;
import com.p1.application.data.zipTable;
import com.p1.application.service.UserHandler;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@EntityScan(basePackages = "application.data")
@Theme(value = "mytodo")
@PWA(name = "My Todo", shortName = "My Todo", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")


public class Application implements AppShellConfigurator {

  
    public static void main(String[] args) {
        zipTable.setTable(null);

        //CollegeSQL.createCollegeSQL();
        AccountSQL.createAccountSQL();
        //Jsonpasser.dataFromWeb();
        GetSearchTerms.setUp();
        UserHandler.getInstance();
        SpringApplication.run(Application.class, args);
        
    }

}
