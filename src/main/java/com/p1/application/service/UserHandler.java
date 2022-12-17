package com.p1.application.service;


import org.springframework.stereotype.Component;

import com.p1.application.data.Account;
import com.p1.application.data.UserData;


/**
 * The Class UserHandler.
 */
@Component
public class UserHandler {

    /** The singleton. */
    private static UserHandler singleton=null;
    
    /** The data. */
    private UserData data;

    /**
     * Instantiates a new user handler.
     */
    private UserHandler(){
        data= new UserData();
    }

    /**
     * Gets the single instance of UserHandler.
     *
     * @return single instance of UserHandler
     */
    public static UserHandler getInstance(){
        if(singleton==null){
            singleton= new UserHandler();
        }
        return singleton;
    }

    /**
     * Gets user data hashmap.
     *
     * @return the data
     */
    public UserData getData(){
        return data;
    }

    /**
     * Sets the data.
     *
     * @param college the new data
     */
    public void setData(UserData college){
        this.data=college;
    }

    /**
     * Returns true if ip of the account ascoiated with account being accesed is equal
     *
     * @param ip the ip
     * @param account the account
     * @return true, if successful
     */
    public boolean tryingToBreakIn(String ip, Account account){
        if(data.getAccount(ip).getId()==account.getId()){
            return false;
        }
        return true;
    }

}