package com.example.user16.firedbnext;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user16 on 2017/02/21.
 */

public class designHouse extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<House> allHouses;
    /* private final String age; */


    public designHouse(Activity context, ArrayList<House> allHouses)
    {
        super(context, R.layout.list_all_car_data);
        this.context = context;
        this.allHouses = allHouses;
    }

    /*  overloaded method:   */

    public View getView(int pos, View view, ViewGroup parent)
    {
        LayoutInflater infiltrator =  context.getLayoutInflater();
        View rowView = infiltrator.inflate(R.layout.list_all_house_data, null, true);

        TextView addressVal = (TextView) rowView.findViewById(R.id.addressVal);
        TextView cityVal = (TextView) rowView.findViewById(R.id.cityVal);
        TextView countryVal = (TextView) rowView.findViewById(R.id.countryVal);
        TextView postCodeVal = (TextView) rowView.findViewById(R.id.postcodeVal);
        TextView provinceVal = (TextView) rowView.findViewById(R.id.provinceVal);

        /* TextView ageVal = (TextView) rowView.findViewById(R.id.ageVal); */

        House oneHouse = allHouses.get(pos);
        addressVal.setText(oneHouse.getAddress());
        cityVal.setText(oneHouse.getCity());
        countryVal.setText( oneHouse.getCountry() );
        postCodeVal.setText(oneHouse.getProvince());
        provinceVal.setText( Integer.valueOf( oneHouse.getPostCode() ));

        return rowView;

    }
}
