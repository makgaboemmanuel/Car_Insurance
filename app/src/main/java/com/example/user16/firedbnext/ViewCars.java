package com.example.user16.firedbnext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewCars extends AppCompatActivity {

    ListView lstCars;
    DatabaseReference dbCars;
    FirebaseUser thisUser;
    int alCars;
    design_Car theCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cars);

        thisUser = FirebaseAuth.getInstance().getCurrentUser();
        dbCars = FirebaseDatabase.getInstance().getReference().child(thisUser.getUid()).child("Car");
        alCars = 0;

        /*    read cars method  */

        readCars();
        /* lstCars.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewCars.this, "wada wada wada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); */
    }


    public void readCars() {
        dbCars.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*  necessary variables:         */
                alCars = Integer.valueOf(String.valueOf(dataSnapshot.getChildrenCount()));
                String[] one = new String[alCars];
                String[] two = new String[alCars];
                String[] three = new String[alCars];
                String[] four = new String[alCars];
                String[] five = new String[alCars];

                /* new added information */
                double [] six = new double[alCars];
                double [] seven = new double[alCars];
                double [] eight = new double[alCars];
                double [] nine = new double[alCars];
                String [] ten = new String[alCars];
                String [] eleven = new String[alCars];
                String [] twelve = new String[alCars];
                String [] thirteen = new String[alCars];
                String [] fourteen = new String[alCars];
                String [] fifteen = new String[alCars];
                String [] sixteen = new String[alCars];
                String [] seventeen = new String[alCars];
                String [] eighteen = new String[alCars];





                int myC = 0;

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                      /*  Toast.makeText(LeonJay.this, "Address:          " + postSnapshot.getKey() + ",   " +  postSnapshot.child("address").getValue() +
                                ",      City:      " + postSnapshot.child("city").getValue() , Toast.LENGTH_SHORT).show(); */

                    /* }  */
                        /* Log.i("All Values:  ", dataSnapshot.toString()); */
                    one[myC] = postSnapshot.child("brand").getValue().toString();
                    two[myC] = postSnapshot.child("color").getValue().toString();
                    three[myC] = postSnapshot.child("distance").getValue().toString();
                    four[myC] = postSnapshot.child("model").getValue().toString();
                        /*  five[myC] =  postSnapshot.child("postCode").getValue().toString( );  */
                    five[myC] = postSnapshot.child("years").getValue().toString();
                        /* House house = new House( one[myC], two[myC], three[myC], four[myC], Integer.valueOf( five[myC] ) , 0 ); */

                    /*  new addedd information */
                    six[myC] =  Double.parseDouble(  postSnapshot.child("totalInsuredAmount").getValue().toString() );
                    seven[myC] = Double.parseDouble(  postSnapshot.child("passMaxCover").getValue().toString());
                    eight[myC] = Double.parseDouble(  postSnapshot.child("totalInsuredAmount").getValue().toString() );
                    nine[myC] = Double.parseDouble( postSnapshot.child("veMaxCover").getValue() .toString() );
                    ten[myC] = postSnapshot.child("insuredFor").getValue().toString();
                    eleven[myC] = postSnapshot.child("classOfUse").getValue().toString();
                    twelve[myC] = postSnapshot.child("excess").getValue().toString();
                    thirteen[myC] = postSnapshot.child("coverType").getValue().toString();
                    fourteen[myC] = postSnapshot.child("totalLoss").getValue().toString();
                    fifteen[myC] = postSnapshot.child("totalInsuredAmount").getValue().toString();
                    sixteen[myC] = postSnapshot.child("thirdParty").getValue().toString();
                    seventeen[myC] = postSnapshot.child("roadAssist").getValue().toString();
                    eighteen[myC] = postSnapshot.child("towAway").getValue().toString();

                    /*  new addedd information */


                    Log.i("All Values:  ", one[myC]);
                    myC++;


                }

                lstCars = (ListView) findViewById(R.id.viewCarData);
                theCars = new design_Car(ViewCars.this, one, two, three, four,
                                                        five, six, seven, eight,
                                                        nine, ten, eleven, twelve,
                                                        thirteen, fourteen, fifteen,
                                                        sixteen, seventeen, eighteen);
                lstCars.setAdapter(theCars);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
