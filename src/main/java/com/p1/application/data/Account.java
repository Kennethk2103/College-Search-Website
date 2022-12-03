package com.p1.application.data;

import java.io.File;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Account {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private String Username;
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private String Email;
    public String getEmail() {
        return Email;
    }
    private String favorites;
    public Account(int id, String username, String password, String email, String favorites) {
        this.id = id;
        Username = username;
        this.password = password;
        Email = email;
        this.favorites = favorites;
    }
    public Account(String email,String password){
        Username= email.substring(0,email.indexOf("@"));
        this.Email=email;
        this.password=password;
        favorites="";
    }
    public String getFavorites() {
        return favorites;
    }
    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    private Integer Zip;
    public Integer getZip() {
        return Zip;
    }
    public void setZip(Integer zip) {
        Zip = zip;
    }
    private Double GPA;
    public Double getGPA() {
        return GPA;
    }
    public void setGPA(Double gPA) {
        GPA = gPA;
    }
    private Double SAT;
    public Double getSAT() {
        return SAT;
    }
    public void setSAT(Double sAT) {
        SAT = sAT;
    }
    private Double ACT;
    public Double getACT() {
        return ACT;
    }
    public void setACT(Double aCT) {
        ACT = aCT;
    }
    private byte[] file;
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }

    
}
