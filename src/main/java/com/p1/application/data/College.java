package com.p1.application.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

@EntityScan
public class College {
    private int id;
    private String name;
    private int read25;
    public int getRead25() {
        return read25;
    }
    public void setRead25(int read25) {
        this.read25 = read25;
    }
    private int read75;
    public int getRead75() {
        return read75;
    }
    public void setRead75(int read75) {
        this.read75 = read75;
    }
    private int math25;
    public int getMath25() {
        return math25;
    }
    public void setMath25(int math25) {
        this.math25 = math25;
    }
    private int math75;
    private int upper;
    public int getUpper() {
        return upper;
    }
    public void setUpper(int upper) {
        this.upper = upper;
    }
    private int lower;
    public int getLower() {
        return lower;
    }
    public void setLower(int lower) {
        this.lower = lower;
    }
    public College(int id, String name, double read25, double read75, double math25, double math75) {
        this.id = id;
        this.name = name;
        this.read25 = (int)read25;
        this.read75 = (int)read75;
        this.math25 = (int)math25;
        this.math75 = (int)math75;
        upper = (int)(math75+read75);
        lower = (int)(math25+read25);

    }
    public int getMath75() {
        return math75;
    }
    public void setMath75(int math75) {
        this.math75 = math75;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
