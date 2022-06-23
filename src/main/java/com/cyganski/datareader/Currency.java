package com.cyganski.datareader;

import java.util.Date;

public class Currency {
    private String data;
    private String name;
    private double value;

    public Currency(String data, String name, double value) {
        this.data = data;
        this.name = name;
        this.value = value;
    }
	public String getDate(){
        return data;
    }
    public void setDate(String data){
        this.data = data;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getValue(){
        return value;
    }
    public void setValue(double value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "Data=" + data +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
