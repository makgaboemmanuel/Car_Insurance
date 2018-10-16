package com.example.user16.firedbnext;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by user16 on 2017/02/23.
 */

public class design_Car extends ArrayAdapter<String> {
    private final Context context;
    private final String cBrand[];
    private final String cColor[];
    private final String cDistance[];
    private final String cModel[];
    private final String cYears[];

    /*  new added features  */

    private final double cTotalInsuredAmount[];
    private final double cPassMaxCover[];
    private final double cVeMaxCover[];
    private final double cInsuredFor[];
    private final String cClassOfUse[];
    private final String cExcess[];
    private final String cCoverType[];
    private final String cTotalLoss[];
    private final String cThirdParty[];
    private final String cRoadAssist[];
    private final String cTowAway[];
    private final String cEmer_accom[];
    private final String cTrackDevice[];

    /*  new addedd features   */


    /* public design_Car(){}; */

    public design_Car(Activity context, String cBrand[], String cColor[], String cDistance[], String cModel[], String cYears[],
                      /* new added features */   double cTotalInsuredAmount[], double cPassMaxCover[],
                      double cVeMaxCover[], double cInsuredFor[], String cClassOfUse[], String cExcess[], String cCoverType[],
                       String cTotalLoss[], String cThirdParty[], String cRoadAssist[], String cTowAway[], String cEmer_accom[],
                       String cTrackDevice[] /* new added features */ )
    {
        super(context,  R.layout.cars_more, cBrand);
        this.context = context;
        this.cBrand = cBrand;
        this.cColor = cColor;
        this.cDistance = cDistance;
        this.cModel = cModel;
        this.cYears = cYears;

        /*  new added features */

        this.cTotalInsuredAmount = cTotalInsuredAmount;
        this.cPassMaxCover = cPassMaxCover;
        this.cVeMaxCover = cVeMaxCover;
        this.cInsuredFor = cInsuredFor;
        this.cClassOfUse = cClassOfUse;
        this.cExcess  = cExcess ;
        this.cCoverType = cCoverType;
        this.cTotalLoss = cTotalLoss;
        this.cThirdParty = cThirdParty;
        this.cRoadAssist = cRoadAssist;
        this.cTowAway = cTowAway;
        this.cEmer_accom = cEmer_accom;
        this.cTrackDevice = cTrackDevice ;
        /*  new added  features */
    }

   public View getView(int pos, View view, ViewGroup parent)
    {
        /*     LayoutInflater infiltrator =  context.getLayoutInflater();  */

        LayoutInflater infiltrator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);// getLayoutInflater ();
        View rowView = infiltrator.inflate(R.layout.cars_more, null, true);
        TextView cbrand = (TextView) rowView.findViewById(R.id.brandVal);
        TextView ccolor = (TextView) rowView.findViewById(R.id.colorVal);
        TextView cdistance = (TextView) rowView.findViewById(R.id.distanceVal);
        TextView cmodel= (TextView) rowView.findViewById(R.id.modelVal);
        TextView cyears = (TextView) rowView.findViewById(R.id.yearsVal);

        /*      new values added */

        TextView ctotalInsuredAmount = (TextView) rowView.findViewById(R.id.totalInsuredAmountVal);
        TextView cpassMaxCover = (TextView) rowView.findViewById(R.id.passMaxCoverVal);
        TextView cveMaxCover = (TextView) rowView.findViewById(R.id.veMaxCoverVal);
        TextView cinsuredFor = (TextView) rowView.findViewById(R.id.insuredForVal);
        TextView cclassOfUse = (TextView) rowView.findViewById(R.id.classOfUseVal);
        TextView cexcess = (TextView) rowView.findViewById(R.id.excessVal);
        TextView ccoverType = (TextView) rowView.findViewById(R.id.coverTypeVal);
        TextView ctotalLoss = (TextView) rowView.findViewById(R.id.totalLossVal);
        TextView cthirdParty = (TextView) rowView.findViewById(R.id.thirdPartyVal);
        TextView croadAssist = (TextView) rowView.findViewById(R.id.roadAssistVal);
        TextView ctowAway= (TextView) rowView.findViewById(R.id.towAwayVal);
        TextView cemer_accom = (TextView) rowView.findViewById(R.id.emer_accomVal);
        TextView ctrackDevice = (TextView) rowView.findViewById(R.id.trackDeviceVal);

        /*  new values added */

        /*  set the text for the textviews:  */
        cbrand.setText(cBrand[pos]);
        cmodel.setText(cModel[pos]);
        ccolor.setText(cColor[pos]);
        cdistance.setText(cDistance[pos]);
        cyears.setText(cYears[pos]);

        /*  new values added */
        ctotalInsuredAmount.setText( String.valueOf( cTotalInsuredAmount[pos] ));
        cpassMaxCover.setText( String.valueOf(cPassMaxCover[pos]  ));
        cveMaxCover.setText( String.valueOf(cVeMaxCover[pos]  ));
        cinsuredFor.setText( String.valueOf( cInsuredFor[pos] ) );
        cclassOfUse.setText( cClassOfUse[pos]  );
        cexcess.setText( cExcess[pos]  );
        ccoverType.setText( cCoverType[pos]  );
        ctotalLoss.setText( cTotalLoss[pos]  );
        cthirdParty.setText( cThirdParty[pos]  );
        croadAssist.setText( cRoadAssist[pos]  );
        ctowAway.setText( cTowAway[pos]  );
        cemer_accom.setText( cEmer_accom[pos]  );
        ctrackDevice.setText(  cTrackDevice[pos] );

        /*  new values added */

        return rowView;

    }
}
