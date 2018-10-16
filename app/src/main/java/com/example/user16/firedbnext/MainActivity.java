package com.example.user16.firedbnext;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener; /*    correct */
import com.google.android.gms.tasks.Task; /*    correct */
import com.google.firebase.auth.AuthResult; /*    correct */
import com.google.firebase.auth.FirebaseAuth; /*    correct */

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/*  https://firemorenext.firebaseio.com/ */

public class MainActivity extends AppCompatActivity {

    /*  global declarations */
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("firemorenext");

    private FirebaseAuth auth;

  /*    variables for components used */
    String emailIn, passIn;
    EditText emailText, passText;
    TextView signText;

     ProgressDialog progressDial;
    private FirebaseAuth firebaseAuth;
   //  auth = FirebaseAuth.getInstance();

    Animation anim_blink;
    ImageView blinklogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blinklogo = (ImageView) findViewById(R.id.onelogo);
        anim_blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinkybill);
        blinklogo.setAnimation(anim_blink);


        firebaseAuth = FirebaseAuth.getInstance();

        /*        */
         mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Intent aintent = new Intent(MainActivity.this,LeonJay.class);

                    startActivity(aintent);

                } else {

                    Toast.makeText(MainActivity.this, "Enter Details", Toast.LENGTH_LONG).show();
                }
            }
        };




        emailText = (EditText) findViewById(R.id.txtEMail);
        passText = (EditText) findViewById(R.id.txtPassVal);
        signText = (TextView) findViewById(R.id.txtViewSignIn);

         progressDial = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        emailIn = "";
        passIn = "";
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public  void signWithEmail(View view)
    {
        /*  get the password variables */

        emailIn = emailText.getText().toString().trim();
        passIn = passText.getText().toString().trim();

        if (TextUtils.isEmpty( emailIn ))
        {
            Toast.makeText(MainActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            /*  stopping the function execution */
            return;
        }

        if (TextUtils.isEmpty( passIn))
        {
            Toast.makeText(MainActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            /*  stopping the function execution */
            return;
        }

        progressDial.setMessage("Registering User......");
        progressDial.show();
        firebaseAuth.createUserWithEmailAndPassword(emailIn, passIn).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful() )
                {
                    Toast.makeText(MainActivity.this, "User Successfully Registered", Toast.LENGTH_SHORT).show();

                    /*  start with the terms and conditions first before the main activity:  */

                    Intent mrotate = new Intent(MainActivity.this, terms_conditions.class);
                    startActivity(mrotate);

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Could Not Register User", Toast.LENGTH_SHORT).show();
                }
            }
        }) ;
    }

    /*  options menu:        */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.credits:

                Intent intent = new Intent(MainActivity.this, credits.class);
                startActivity(intent);

                break;

            case R.id.help:
                Intent intentHelp = new Intent(MainActivity.this, HelpMenu.class);
                startActivity(intentHelp);

                break;
        }
        return super. onOptionsItemSelected(item);
    }

    public void  alReadyReg(View view)
    {

 /*  get the password variables */

        emailIn = emailText.getText().toString().trim();
        passIn = passText.getText().toString().trim();

        if (TextUtils.isEmpty( emailIn ))
        {
            Toast.makeText(MainActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            /*  stopping the function execution */
            return;
        }

        if (TextUtils.isEmpty( passIn))
        {
            Toast.makeText(MainActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            /*  stopping the function execution */
            return;
        }

        progressDial.setMessage("Registering User......");
        progressDial.show();


        /*  when you are already registered then you can just log-in here */
         firebaseAuth.signInWithEmailAndPassword( emailIn, passIn).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    startActivity(new Intent(MainActivity.this,LeonJay.class));
                }
             }
         });

    }

    /*  private user created methods */
    public void registerUser()
    {

        /*  get the password variables */

        emailIn = emailText.getText().toString().trim();
        passIn = passText.getText().toString().trim();

        if (TextUtils.isEmpty( emailIn ))
        {
            Toast.makeText(MainActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            /*  stopping the function execution */
            return;
        }

        if (TextUtils.isEmpty( passIn))
        {
            Toast.makeText(MainActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            /*  stopping the function execution */
            return;
        }

        progressDial.setMessage("Registering User......");
        progressDial.show();


        /*  when you are already registered then you can just log-in here */
        /* firebaseAuth.signInWithEmailAndPassword() */
    }
}
