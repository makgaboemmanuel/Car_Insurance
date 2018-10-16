package com.example.user16.firedbnext;

/**
 * Created by user16 on 2017/02/06.
 */

public class House {

    /* the attributes of the class methods  */
    public String address;
    public String country;
    public String province;
    public String city;
    public int postCode;
    public int years;
   /*  public double price; */

    /*  the methods of the class including the constructor */
    public  House(){}

    public House(String address, String country, String province, String city, int postCode, int years) {
        this.address = address;
        this.country = country;
        this.province = province;
        this.city = city;
        this.postCode = postCode;
        this.years = years;
    }

    public void setHouse(String address, String country, String province, String city, int postCode, int years) {
        this.address = address;
        this.country = country;
        this.province = province;
        this.city = city;
        this.postCode = postCode;
        this.years = years;
    }


    public String getAddress() {  return address; }   /*    getting the value of address attribute     */

    public String getCountry() {  return country; }   /*    getting the value of price attribute     */

    public String getProvince() { return province; } /*    getting the years of years attribute     */

    public String getCity() { return city; }   /*    getting the value of color attribute     */

    public int getPostCode() { return postCode; } /*    getting the value of insurance attribute     */

    public int getYears() { return years; } /*    getting the value of insurance attribute     */

}
