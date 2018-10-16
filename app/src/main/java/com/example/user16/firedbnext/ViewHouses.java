package com.example.user16.firedbnext;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewHouses extends AppCompatActivity {

    FirebaseUser thisUser;
    DatabaseReference dbref;
    /*, dbREF; */

    ListView listHouses;

    /* designHouse adapter; */
    listAllData myAdapter;


    int minMin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_houses_layout);

        thisUser = FirebaseAuth.getInstance().getCurrentUser();
        dbref = FirebaseDatabase.getInstance().getReference().child(thisUser.getUid()).child("House") ;
        minMin = 0;

        readHouses();

    }


    public void readHouses()
    {

        /*   one for getting correct values:   */

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                minMin = Integer.valueOf( String.valueOf(  dataSnapshot.getChildrenCount() ) );
                String [] one = new String[minMin];
                String [] two = new String[minMin];
                String [] three = new String[minMin];
                String [] four = new String[minMin];
                String [] five = new String[minMin];
                String [] six = new String[minMin];
                int myC = 0;

                /* Toast.makeText(ViewHouses.this, ":  " + minMin, Toast.LENGTH_SHORT).show();  */

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        /* Log.i("All Values:  ", dataSnapshot.toString()); */
                        one[myC] = postSnapshot.child("address").getValue().toString( );
                        two[myC] = postSnapshot.child("city").getValue().toString( );
                        three[myC] = postSnapshot.child("country").getValue().toString( );
                        four[myC] = postSnapshot.child("province").getValue().toString( );
                        five[myC] =  postSnapshot.child("postCode").getValue().toString( );
                        six[myC] =   postSnapshot.child("years").getValue().toString( );
                        House house = new House( one[myC], two[myC], three[myC], four[myC], Integer.valueOf( five[myC] ) , 0 );


                        Log.i("All Values:  ", house.getAddress());

                        myC++;

                }

                listHouses = (ListView) findViewById(R.id.viewhousedata);
                myAdapter = new listAllData(ViewHouses.this, one, two, three,  five, four);
                listHouses.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


    }

    public void editHouses(View view)
    {

       /* final Dialog d = new Dialog(this);
        d.setTitle("Provide Details To update");
        d.setContentView(R.layout.edit_car_details);
        d.show(); */

    }

    public void  btnLeoanGo(View view)
    {
        Intent intent = new Intent(ViewHouses.this, LeonJay.class);
        startActivity(intent);
    }
}
