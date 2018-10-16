package com.example.user16.firedbnext;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Mathekga Emmanuel on 3/26/2017.
 */

public class car_res_view extends ArrayAdapter<String>
{
    private final Context context;
    private final String cBrand[];
    private final String cColor[];
    private final String cDistance[];
    private final String cModel[];
    private final String cYears[];

    public car_res_view(Activity context, String cBrand[], String cColor[], String cDistance[], String cModel[], String cYears[] )
    {
        super(context, R.layout.cars_more, cBrand);
        this.context = context;
        this.cBrand = cBrand;
        this.cColor = cColor;
        this.cDistance = cDistance;
        this.cModel = cModel;
        this.cYears = cYears;
    }

    public View getView(int pos, View view, ViewGroup parent)
    {
        /*     LayoutInflater infiltrator =  context.getLayoutInflater();  */

        LayoutInflater infiltrator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);// getLayoutInflater ();
        View rowView = infiltrator.inflate(R.layout.list_all_car_data, null, true);
        TextView cbrand = (TextView) rowView.findViewById(R.id.brandVal);
        TextView ccolor = (TextView) rowView.findViewById(R.id.colorVal);
        TextView cdistance = (TextView) rowView.findViewById(R.id.distanceVal);
        TextView cmodel= (TextView) rowView.findViewById(R.id.modelVal);
        TextView cyears = (TextView) rowView.findViewById(R.id.yearsVal);

        /*  set the text for the textviews:  */
        cbrand.setText(cBrand[pos]);
        cmodel.setText(cModel[pos]);
        ccolor.setText(cColor[pos]);
        cdistance.setText(cDistance[pos]);
        cyears.setText(cYears[pos]);

        return rowView;

    }
}
