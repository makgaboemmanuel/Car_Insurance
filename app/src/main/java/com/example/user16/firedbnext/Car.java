package com.example.user16.firedbnext;

/**
 * Created by user16 on 2017/02/22.
 */

public class Car {
    private double stPrice;
    private String brand;
    private String model;
    private String color;
    private int years;
    private float distance;
    private double newValue;
    private String carRegNo;
    private String premiums;
    private String status;
    Car(){};

    public void setCar(double stPrice, String brand, String model, String color, int years, float distance, String carRegNo)
    {
        this.stPrice = stPrice;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.years = years;
        this.distance = distance;
        this.carRegNo = carRegNo;
        status = "Under Review";
        newValue = stPrice -  ( years * 2500 ) - (  distance * 0.29 );

        premiums = "Can't Insure, Over 1M / Less 180k" ;

        if ( newValue >= 180000.00 && newValue <= 270000.00 )
        { premiums = Double.toString( 530.00) ; }

        if ( newValue >= 270001.00 && newValue <= 360000.00 )
        { premiums = Double.toString( 580.00) ; }

        if ( newValue >= 360001.00 && newValue <= 450000.00 )
        { premiums = Double.toString( 630.00 ) ; }

        if ( newValue >= 450001.00 && newValue <= 540000.00 )
        { premiums = Double.toString( 680.00 ); }

        if ( newValue >= 540001.00 && newValue <= 630000.00 )
        { premiums = Double.toString( newValue) ; }

        if ( newValue >= 630001.00 && newValue <= 720000.00 )
        { premiums = Double.toString( newValue) ; }

        if ( newValue >= 720001.00 && newValue <= 1000000.00 )
        { premiums = Double.toString( newValue) ; }

        /* else{premiums = "Can't Insure, Over 1M / Less 180k" ;} */
    }

    public String getBrand() {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public String getColor()
    {
        return color;
    }

    public  int getYears()
    {
        return  years;
    }

    public float getDistance()
    {
        return distance;
    }

    public double getNewValue()
    {
        return newValue;
    }

    public double  getStPrice() {return stPrice ;}

    public String getCarRegNo() {return carRegNo;}

    public  String getStatus() {return status; }

    public String getPremiums() {return premiums;}

    public double calcNewValue()
    {
        newValue = stPrice -  ( years * 2500 ) - (  distance * 0.29 );
        return newValue;
    }

}
