package com.example.user16.firedbnext;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class listView extends AppCompatActivity {
    FirebaseUser thisUser;
    DatabaseReference dbref;

    ProgressDialog progressDial;
    String genderChoice;
    LinearLayout editField;
    TextView a, b, c, d, e, f;

    Animation myfades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        myfades = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadetoo);
        genderChoice = "";
        progressDial = new ProgressDialog(this);

        editField = (LinearLayout) findViewById(R.id.edPsn); 
        thisUser = FirebaseAuth.getInstance().getCurrentUser();
        dbref = FirebaseDatabase.getInstance().getReference().child(thisUser.getUid()).child("Person") ;


        /*  implement the read activity here please:         */

        a = (TextView) findViewById(R.id.fnameVal);
        b = (TextView) findViewById(R.id.surnameVal);
        c = (TextView) findViewById(R.id.idnoVal);
        d = (TextView) findViewById(R.id.dateVal);
        e = (TextView) findViewById(R.id.genderVal);
        f = (TextView) findViewById(R.id.ageVal);

        a.setAnimation(myfades); b.setAnimation(myfades);  c.setAnimation(myfades);
        d.setAnimation(myfades); e.setAnimation(myfades); f.setAnimation(myfades);

        defaultRead();


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (dbref != null) {

        }
    }


    public void chooseMale(View view)
    {
        genderChoice = "Male";
    }


    public void chooseFemale(View view)
    {
        genderChoice = "Female";
    }


public void changeMDetails(View view)
{
    final Dialog dd = new Dialog(this);
    dd.setTitle("Edit Your Details Here");
    dd.setContentView(R.layout.edit_person);

    final EditText newFName = (EditText) dd.findViewById(R.id.valFN);
    final EditText newLName = (EditText) dd.findViewById(R.id.valLN);
    final EditText newIDNO = (EditText) dd.findViewById(R.id.validno);

    Button upDateInfo = (Button) dd.findViewById(R.id.btnFromDialog);
    upDateInfo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!(TextUtils.isEmpty(newFName.getText().toString())))
            {
                if ( newFName.getText().toString().length() >= 3)
                {
                    Person m = new Person();
                    m.setFirstName(newFName.getText().toString());
                    dbref.child("firstName").setValue(m.getFirstName());
                    Toast.makeText(getApplicationContext(), "Firstname Update Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LeonJay.class);
                    startActivity(intent);
            }    else
                { Toast.makeText(getApplicationContext(), "Your Firstname Is Wrong", Toast.LENGTH_SHORT).show();  } }


            if (!(TextUtils.isEmpty( newLName.getText().toString())))
            { if ( newLName.getText().toString().length() >= 3)
            {
                Person m = new Person();
                m.setSurname(newLName.getText().toString());
                dbref.child("surname").setValue(m.getSurname());
                Toast.makeText(getApplicationContext(), "Surname Update Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LeonJay.class);
                startActivity(intent);
            }
            else
            {    Toast.makeText(getApplicationContext(), "Your Lastname Is Wrong", Toast.LENGTH_SHORT).show(); } }

            /*  Updating The ID*/
            if(!(TextUtils.isEmpty(newIDNO.getText().toString())))
            { if ( newIDNO.getText().toString().length() == 13)
            {
                Person m = new Person();
                int idresult = m.validaIDNO(newIDNO.getText().toString());

                if (idresult == 2)
                {
                    m.setIdNo(newIDNO.getText().toString());
                    dbref.child("idNo").setValue(m.getIdNo());
                    dbref.child("dateOfB").setValue(m.getDateOfB());
                    dbref.child("theAGe").setValue(m.getTheAGe());
                    /* dbref.child("idNo").setValue(m.getIdNo()); */
                    Toast.makeText(getApplicationContext(), "IDNo, Age, DateOfB Update Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LeonJay.class);
                    startActivity(intent);
                }
                else
                {   Toast.makeText(getApplicationContext(), "Your ID Is Wrong", Toast.LENGTH_SHORT).show(); } }
            }


        }
    });
    dd.show();


}
    public void defaultRead()
    {


        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {

                    Log.i("Ygritte", dataSnapshot.toString());
                    a.setText( dataSnapshot.child("firstName").getValue().toString() );
                    b.setText( dataSnapshot.child("surname").getValue().toString() );
                    c.setText( dataSnapshot.child("idNo").getValue().toString() );
                    d.setText( dataSnapshot.child("dateOfB").getValue().toString()  );
                    e.setText( dataSnapshot.child("gender").getValue().toString() );
                    f.setText(dataSnapshot.child("theAGe").getValue().toString());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }
}
