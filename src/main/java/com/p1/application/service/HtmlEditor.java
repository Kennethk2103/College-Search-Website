package com.p1.application.service;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.page.Page;

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

    // public static void addAttribute(Page page, HtmlComponent component, String atr){
    //     String [] arr= atr.split(" ");
    //     for(int i =0;i<arr.length;i++){
    //         if(arr[i].contains("id")){
    //             String arr2[] = arr[i].split("=");
    //             component.setId(arr2[1]);
    //             arr[i]="";
    //         }
    //     }

    //     String JQuerry = "$('#" + component.getId()+ "').attr(";
    //     for(int i =0;i<arr.length;i++){
    //         JQuerry+="'" + arr[i] +"',";
    //     }
    //     JQuerry+=");";
    //     page.executeJs(JQuerry);
    // }
}
