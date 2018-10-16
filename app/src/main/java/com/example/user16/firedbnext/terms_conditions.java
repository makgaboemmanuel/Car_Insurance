package com.example.user16.firedbnext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class terms_conditions extends AppCompatActivity {

    /*  global variables:  */

    CheckBox termsCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        /*  intialization: */
        termsCheck = (CheckBox) findViewById(R.id.chTerms);


    }


    public void btnViewTerms(View view)
    {
        if ( termsCheck.isChecked() )
        {
            Intent intent = new Intent(terms_conditions.this, LeonJay.class);
            startActivity(intent);
        }

        else {
            Toast.makeText(this, "You Must Agree To Terms And Conditions ", Toast.LENGTH_SHORT).show();
        }
    }

}
