package com.example.user16.firedbnext;

/**
 * Created by Mathekga Emmanuel on 3/26/2017.
 */

public class Car_Completed {

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

    /*     new additions: */
    private double  totalInsuredAmount; /*  268,500 : THIS ACTUALLY IS THE newValuevariable in your system*/
    private double  passMaxCover;       /*  5,000,000 */
    private double  veMaxCover;         /*  5,000,000 */
    private String  insuredFor;         /*  market value */
    private String  classOfUse;         /*  private    */
    private double  excess;             /*  3500 */

    /*  covered for  */
    private String  coverType;          /*  yes / no */
    private String  totalLoss;          /*  yes / no */
    private String  thirdParty;         /*  yes / no */

    /*  benefits */
    private String roadAssist;          /* yes / no */
    private String towAway;             /* yes / no */
    private String emer_accom;          /* yes / no */
    private String trackDevice;         /* yes / no */

    /*  end of new additions  */
    Car_Completed(){};

    Car_Completed( double stPrice, String brand, String model, String color, int years, float distance, String carRegNo )
    {
        this.stPrice = stPrice;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.years = years;
        this.distance = distance;
        this.carRegNo = carRegNo;
        status = "Provisionally Approved";

        /*  new additions  */

        totalInsuredAmount =    268500;
        passMaxCover =          5000000;
        veMaxCover =            5000000;
        insuredFor =            "Market Value";
        classOfUse =            "Private";
        excess =                4200.00;
        /*      new additions   */

        /*  Covered For */
        coverType ="Comprehensive";
        totalLoss ="NO";
        thirdParty ="NO";


        /*  covered for  */

        /*  benefits */

        roadAssist ="NO";
        towAway ="NO";
        emer_accom ="NO";
        trackDevice ="NO";

        /*  benefits */

        newValue = stPrice -  ( years * 2500 ) - (  distance * 0.29 );

        premiums = Double.toString( 2300.00) ;

        if ( newValue >= 180000.00 && newValue <= 270000.00 )
        { premiums = Double.toString( 2500.00) ; }

        if ( newValue >= 270001.00 && newValue <= 360000.00 )
        { premiums = Double.toString( 2700.00) ; }

        if ( newValue >= 360001.00 && newValue <= 450000.00 )
        { premiums = Double.toString( 2900.00 ) ; }

        if ( newValue >= 450001.00 && newValue <= 540000.00 )
        { premiums = Double.toString( 3100.00); }

        if ( newValue >= 540001.00 && newValue <= 630000.00 )
        { premiums = Double.toString( 3300.00) ; }

        if ( newValue >= 630001.00 && newValue <= 720000.00 )
        { premiums = Double.toString( 3500.00) ; }

        if ( newValue >= 720001.00 && newValue <= 1000000.00 )
        { premiums = Double.toString( 3700.00) ; }
    }
    public void setCar_Completed(double stPrice, String brand, String model, String color, int years, float distance, String carRegNo)
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
