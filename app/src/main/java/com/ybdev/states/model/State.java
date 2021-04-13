package com.ybdev.states.model;

import java.util.ArrayList;

public class State {

    private String name = "";
    private ArrayList<String> borders = null;
    private String nativeName = "";
    private String flag = "";
    private ArrayList<Float> latlng;
    private float area;
    private String alpha3Code = "";

    public State() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ArrayList<Float> getLatlng() {
        return latlng;
    }

    public void setLatlng(ArrayList<Float> latlng) {
        this.latlng = latlng;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public float getMapZoomLevel(){
        if (getArea() < 1000)
            return 8f;
        else if(getArea() < 5000)
            return 7.5f;
        else if(getArea() < 10000)
            return 7f;
        else if(getArea() < 25000)
            return 6.5f;
        else if(getArea() < 50000)
            return 6f;
        else if(getArea() < 100000)
            return 5.5f;
        else if(getArea() < 500000)
            return 5.15f;
        else if(getArea() < 1000000)
            return 4.95f;
        else if(getArea() < 2500000)
            return 4.65f;
        else if(getArea() < 5000000)
            return 3.475f;
        else if(getArea() < 50000000)
            return 3;
        return  1.5f;
   }
}