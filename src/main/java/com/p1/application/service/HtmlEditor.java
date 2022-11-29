package com.p1.application.service;

import com.vaadin.flow.component.HtmlComponent;

public class HtmlEditor {

    public static void addAttribute(HtmlComponent component, String atr){
        String atr2 = atr.replaceAll("\"", "");
        String [] arr= atr2.split(" ");
        for(int i=0;i<arr.length;i++){
            String arr2[]=arr[i].split("=");
            if(arr2[0].equals("class")){
                component.addClassName(arr2[1]);
            }
            else if(arr2.length==1){ 
                component.addClassName(arr2[0]);
            }
            else if(arr2[0].equals("id")){
                component.setId(arr2[1]);
            }
            else{
                component.getElement().setAttribute(arr2[0], arr2[1]);
            }
        }
    }

}
