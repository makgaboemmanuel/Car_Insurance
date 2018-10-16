package com.example.user16.firedbnext;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by user16 on 2017/02/16.
 */

public class listAllData extends ArrayAdapter<String> {
    private final Activity context;
    private final String address[];
    private final String city[];
    private final String country[];
    private final String postCode[];
    private final String province[];
    /* private final String age; */


    public listAllData(Activity context, String address[], String city[], String country[], String postCode[], String province[])
    {
        super(context, R.layout.list_all_house_data, address);
        this.context = context;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.province = province;
    }

    /*  overloaded method:   */

    public View getView(int pos, View view, ViewGroup parent)
    {
        LayoutInflater infiltrator =  context.getLayoutInflater();
        View rowView = infiltrator.inflate(R.layout.list_all_house_data, null, true);

        TextView addressVal = (TextView) rowView.findViewById(R.id.addressVal );
        TextView cityVal = (TextView) rowView.findViewById(R.id.cityVal);
        TextView countryVal = (TextView) rowView.findViewById(R.id.countryVal);
        TextView postCodeVal = (TextView) rowView.findViewById(R.id.postcodeVal);
        TextView provinceVal = (TextView) rowView.findViewById(R.id.provinceVal);
        /* TextView ageVal = (TextView) rowView.findViewById(R.id.ageVal); */

        addressVal.setText(address[pos]);
        cityVal.setText(city[pos]);
        countryVal.setText(country[pos]);
        postCodeVal.setText(postCode[pos]);
        provinceVal.setText(province[pos]);

        return rowView;

    }
}
