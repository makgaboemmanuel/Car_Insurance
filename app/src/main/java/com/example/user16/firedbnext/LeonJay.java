package com.example.user16.firedbnext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/*  more libraries */
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener; /*    correct */
import com.google.android.gms.tasks.Task; /*    correct */
import com.google.firebase.auth.AuthResult; /*    correct */
import com.google.firebase.auth.FirebaseAuth; /*    correct */

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/*  more libraries */

public class LeonJay extends AppCompatActivity {

    /*  global variables */
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser thisUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference personDB;
    String aname = "a";
    String aemail = "";

     TextView txtuname;

  LinearLayout myfade;
    Animation thatfade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leon_jay);

        /*   Appl the animation here please  */
        myfade = (LinearLayout) findViewById(R.id.allfade);
        thatfade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        myfade.setAnimation(thatfade);
        /*  end of amination  */
       /* txtuname =  ( TextView ) findViewById(R.id.txtuname); */
        thisUser = FirebaseAuth.getInstance().getCurrentUser();
        personDB = FirebaseDatabase.getInstance().getReference().child(thisUser.getUid());



        /*  get the currently looged-in user's information here please  */

        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
           public void onAuthStateChanged( @NonNull FirebaseAuth firebaseAuth )
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if( user != null)
                {
                    txtuname.setText("Signed In As:     " + user.getEmail());
                    Toast.makeText(LeonJay.this, "You Are Logged-In As:  " + user.getUid(), Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(LeonJay.this, "Please Log In First", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }


    public  void  SignOut(View view)
    {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(LeonJay.this,MainActivity.class));
        Toast.makeText(LeonJay.this, "You Are Signing Out", Toast.LENGTH_LONG).show();
    }

    public void crtHouse(View view)
    {
        Intent miIntent = new Intent( LeonJay.this, activity_enter_house.class);
        startActivity(miIntent);
    }

    public void crtPerson(View view)
    {

        personDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Person"))
                {
                    Toast.makeText(getApplicationContext(), "Info Already Registered. Only Update Allowed", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Intent personIntent = new Intent(LeonJay.this, EnterPerson.class);
                    startActivity(personIntent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*  first make sure there arent aly personal already in the system */

        /* if (  personDB.child("Person") != null )
        {  }

        else {

        } */
    }

    public void crtCar(View view)
    {
        Intent carIntent = new Intent(LeonJay.this, CarCreate.class);
        startActivity(carIntent);
    }

    public void viewYourself(View view)
    {
        Intent intent = new Intent(LeonJay.this, listView.class);
        startActivity(intent);
    }

    public void viewCars(View view)
    {
        Intent carIntent = new Intent(LeonJay.this, ViewCars.class);
        startActivity(carIntent);
    }

    public void viewHouses(View view)
    {
        Intent intent = new Intent(LeonJay.this, ViewHouses.class);
        startActivity(intent);
    }

    /*  options menu:        */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_leo, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.credits:

                Intent intent = new Intent(LeonJay.this, credits.class);
                startActivity(intent);

                break;

            case R.id.signOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(LeonJay.this,MainActivity.class));
                Toast.makeText(LeonJay.this, "Signed Out", Toast.LENGTH_LONG).show();
                break;

            case R.id.help:

                Intent intentHelp = new Intent(LeonJay.this, HelpMenu.class);
                startActivity(intentHelp);

                break;
        }
        return super. onOptionsItemSelected(item);
    }

}
