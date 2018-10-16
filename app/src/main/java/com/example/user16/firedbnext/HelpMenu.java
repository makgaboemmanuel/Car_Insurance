package com.example.user16.firedbnext;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HelpMenu extends AppCompatActivity {

    EditText eMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_menu);
        eMessage = (EditText) findViewById(R.id.emailMessage);
    }

    public void makeACall(View view)
    {
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+27783755327"));
        startActivity(i);
    }

    public void  sendYourEmail(View view)
    {

              String to = "gudlukmme@gmail.com";
			  String subject = "Property Insurance Help Request: ";
			  String message = eMessage.getText().toString();

			  Intent email = new Intent(Intent.ACTION_SEND);
			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
			  //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
			  //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
			  email.putExtra(Intent.EXTRA_SUBJECT, subject);
			  email.putExtra(Intent.EXTRA_TEXT, message);

			  //need this to prompts email client only
			  email.setType("message/rfc822");

			  startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}
