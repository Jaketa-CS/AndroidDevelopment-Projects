package com.example.hw7_converters;

public class Convert {
    private String name;
    private String c1;
    private String c2;

    public static final Convert[] conversions = {
            new Convert("Fahrenheit to Celsius","Fahrenheit","Celsius"),
            new Convert("Miles to Kilometers","Miles","Kilometers"),
            new Convert("Yards to Meters","Yards","Meters"),
            new Convert("Gallons to Liters","Gallons","Liters")
    };

    //Each workout has a name and description
    private Convert(String name, String c1, String c2){
        this.name = name;
        this.c1 = c1;
        this.c2 = c2;
    }

    public String getC1(){
        return c1;
    }
    public String getC2(){
        return c2;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return this.name;
    }
}
