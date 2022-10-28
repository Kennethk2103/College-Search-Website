package com.p1.application.service;

import org.springframework.stereotype.Component;

import com.p1.application.data.College;
@Component
public class CollegeSingleton {

    private static CollegeSingleton singleton=null;
    private College college;

    private CollegeSingleton(){
        college=null;
    }

    public static CollegeSingleton getInstance(){
        if(singleton==null){
            singleton= new CollegeSingleton();
        }
        return singleton;
    }

    public College getCollege(){
        return college;
    }

    public void setCollege(College college){
        this.college=college;
    }

}
