package com.p1.application.service;


import org.springframework.stereotype.Component;

import com.p1.application.data.Account;
import com.p1.application.data.UserData;

@Component
public class UserHandler {

    private static UserHandler singleton=null;
    private UserData data;

    private UserHandler(){
        data= new UserData();
    }

    public static UserHandler getInstance(){
        if(singleton==null){
            singleton= new UserHandler();
        }
        return singleton;
    }

    public UserData getData(){
        return data;
    }

    public void setData(UserData college){
        this.data=college;
    }

    public boolean tryingToBreakIn(String ip, Account account){
        if(data.getAccount(ip).getId()==account.getId()){
            return false;
        }
        return true;
    }

}