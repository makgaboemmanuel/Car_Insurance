package com.example.user16.firedbnext;

import android.widget.Toast;

/**
 * Created by user16 on 2017/02/06.
 */

public class Person {

    private String firstName;
    private String surname;
    private String gender;

    private String idNo;
    /*  derived variebles values:  */
    private String dateOfB;
    private int theAGe;

    public Person()
    {}


    public void setPerson(String firstName, String surname, String gender, String idNo)
    {
        this.firstName= firstName;
        this.surname = surname;
        this.gender = gender;

        this.idNo = idNo;
        this.theAGe = Integer.valueOf(  idNo.substring(0, 2) );
        this.theAGe = 2017  - Integer.valueOf ( "19" + theAGe) ;
        this.dateOfB = idNo.substring(0, 6) ;// + "-" +  idNo.substring(2, 2) + "-" + idNo.substring(4, 2);
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public String getGender()
    {
        return this.gender;
    }

    public String getIdNo()
    {
        return this.idNo;
    }

    public String getDateOfB() { return this.dateOfB; }

    public int getTheAGe(){ return  this.theAGe;}


    /*  the set methods:         */

    public void  setFirstName(String anme)
    {
        firstName = anme;
    }

    public void  setSurname(String anme)
    {
        surname = anme;
    }

    public void  setGender(String anme)
    {
        gender = anme;
    }

    public void  setIdNo(String anme)
    {
        idNo = anme;
        this.theAGe = Integer.valueOf(  idNo.substring(0, 2) );
        this.theAGe = 2017  - Integer.valueOf ( "19" + theAGe) ;
        this.dateOfB = idNo.substring(0, 6);
    }


    /*  validate the IDNO:       */

    public int validaIDNO(String idNo) {
        boolean idStatus = false;
        int count = 0;

        String mystring = "8801135623083";
        String receiveID = idNo;

        if (receiveID.length() == 13 )
        {
        String datOFB = receiveID.substring(0, 6);
        String gender = receiveID.substring(6, 10);
        String born = receiveID.substring(10, 11);

        int uyear = Integer.valueOf(datOFB.substring(0, 2));
        int umonth = Integer.valueOf(datOFB.substring(2, 4));
        int uday = Integer.valueOf(datOFB.substring(4, 6));


        String ugender = "";

        if ((uyear <= 95) && (uyear >= 45)) {

            if ((umonth <= 12) && (umonth >= 1)) {

                if (umonth != 2 && (uday <= 31) && (uday >= 1)) {
                    count++;
                } else if (umonth == 2 && (uday <= 29) && (uday >= 1)) {
                    count++;
                }
            }
        }


        if (Integer.valueOf(gender) < 4999) {
            ugender = "Female";
        }

        if (Integer.valueOf(gender) > 4999) {
            ugender = "Male";
        }

        if ((Integer.valueOf(born) == 0) || (Integer.valueOf(born) == 1)) {
            count++;
        }
    }

        else
        {
            count = 0;
        }

         return  count;
    }


}
