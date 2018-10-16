package com.example.user16.firedbnext;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class EnterPerson extends AppCompatActivity {


    /*  global variables  */
    private DatabaseReference personDB;
    FirebaseUser thisUser;


    EditText genVal, fname, lName, idVal;
    RadioButton fInp, mInp;

    /*  storage functionality implementation */
    private StorageReference mstorage;
    private static final int PICK_IMAGE_REQUEST = 0;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_person);

        thisUser = FirebaseAuth.getInstance().getCurrentUser();
        personDB = FirebaseDatabase.getInstance().getReference().child(thisUser.getUid())/*NB: push creates a new key: .push() */;
        mstorage = FirebaseStorage.getInstance().getReference() ;

        genVal = (EditText) findViewById(R.id.evGender);
        fname = (EditText) findViewById(R.id.evFname);
        lName= (EditText) findViewById(R.id.evLname);
        idVal = (EditText) findViewById(R.id.evID);

        fInp = (RadioButton) findViewById(R.id.femaleChoose) ;
        mInp = (RadioButton) findViewById(R.id.maleChoose);

        /*  this method works fine */
        /* validateIDNO();  */


    }

    /*  implementing onclick */

    public void chooseFemale(View view)
    {
        genVal.setText( ((RadioButton) findViewById(R.id.femaleChoose) ).getText().toString() );
        mInp.setChecked(false);
    }




    public void chooseMale(View view)
    {
        genVal.setText( ((RadioButton) findViewById(R.id.maleChoose) ).getText().toString() );
        fInp.setChecked(false);
    }

    public void ctrYourData(View view)
    {
        /* validateIDNO(); */
        String f = "";
        String l = "";
        String theGee = "";
        String theID = "";
        int provide = 0;
        if ( TextUtils.isEmpty(fname.getText().toString()) || fname.getText().toString().length() < 3)
        {   fname.setError("Name too short"); }
        else {  provide++; f = fname.getText().toString(); }

        if ( TextUtils.isEmpty(lName.getText().toString()) || lName.getText().toString().length() < 3)
        { lName.setError("Surname Too Short"); }
        else{  provide++; l = lName.getText().toString(); }

        if (TextUtils.isEmpty( genVal.getText().toString() )  )
        {  genVal.setError("Select Gender"); }
        else
        { provide++; theGee = genVal.getText().toString(); }

        if (TextUtils.isEmpty( idVal.getText().toString() ) )
        {  idVal.setText("Invalid ID No");  }
        else {  theID = idVal.getText().toString(); }

        /*  declare and object type of the Person class */
        Person newPerson = new Person();
        int validno = newPerson.validaIDNO(theID);

        /*  implement object of Person class */
        if ((validno == 2 )&& ( provide == 3) )
        {

        newPerson.setPerson( f,l,theGee,theID );

        /*  inserting into database: */

        personDB.child("Person").setValue(newPerson);
        Toast.makeText(getApplicationContext(), "Personal Info. Successfully Addedd", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EnterPerson.this, LeonJay.class);
            startActivity(intent);

        }
        else
        {  idVal.setError("Invalid IDNO"); }


    }

    /*  method for file chooser */
    private void showFileChooser()
    {
        Intent chfintent = new Intent();
        chfintent.setType("image/");
        chfintent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(chfintent, "Select File"), PICK_IMAGE_REQUEST);
    }

    private void uploadFile()
    {
        StorageReference storeHere = mstorage.child(thisUser.getUid()).child("IDDOC");
        storeHere.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext() /* EnterPerson.this */, "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "File Not Uploaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*  validate the idno here please:   */

    public void validateIDNO()
    {
        boolean idStatus = false;
        int count = 0;

        String mystring = "8801135623083";
        String receiveID = mystring ;

        String datOFB = receiveID.substring(0, 6); /*   left with 7 characters */
        String gender = receiveID.substring(6, 10); /*   left with 3 characters */
        String born = receiveID.substring(10, 11);   /*   left with 2 characters */


        int uyear = Integer.valueOf( datOFB.substring(0, 2) ) ;
        int umonth = Integer.valueOf(datOFB.substring(2, 4) ) ;
        int uday = Integer.valueOf(datOFB.substring(4, 6) ) ;

        Toast.makeText(getApplicationContext(), "IDNO:     " + receiveID, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Date of Birth:    " + datOFB, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Gender:       " + gender, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Born:     " + born, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Year:     " + uyear, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Month:        " + umonth, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Day:      " + uday, Toast.LENGTH_SHORT).show();

        String ugender = "";
    /*validate the date of birth */

         if ( ( uyear <= 95 ) && ( uyear >= 45 )     )
        {

            if ( ( umonth <= 12 ) && ( umonth >= 1 ) )
            {

                if (umonth != 2 && ( uday <= 31 ) && ( uday >= 1 ))
                {
                    count++;
                }
                else if( umonth == 2 && ( uday <= 29 ) && ( uday >= 1 ) )
                {
                    count++;
                }
            }
        }

        /*    first increment  */


         if ( Integer.valueOf(gender) < 4999 )
        {
            ugender = "Female";
        }

        if ( Integer.valueOf(gender) > 4999 )
        {
            ugender = "Male";
        }

        if( (Integer.valueOf(born) == 0)  || (Integer.valueOf(born) == 1))
        {
            count++;
        }

         /*    second increment  */
          Toast.makeText(getApplicationContext(), "Count Value:        " + count, Toast.LENGTH_SHORT).show();

    }

    public  void btnUploadID(View view)
    {
        Intent chfintent = new Intent();
        chfintent.setType("*/*");

        chfintent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(chfintent, "Select File"), PICK_IMAGE_REQUEST);

        // FirebaseStorage

        /* StorageReference storeHere = mstorage.child(thisUser.getUid()).child("IDDOC");
        UploadTask uploadTask = storeHere.putBytes() */
        /* showFileChooser();  */
        /*            thisUser = FirebaseAuth.getInstance().getCurrentUser();
        personDB = FirebaseDatabase.getInstance().getReference().child(thisUser.getUid())  NB: push creates a new key: .push() ;
        mstorage = FirebaseStorage.getInstance().getReference();
        */
        /* uploadFile(); */
        //File file = MediaStore.Files.getContentUri("");

    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (/* requestCode == PICK_IMAGE_REQUEST && */ resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            FirebaseStorage theStore = FirebaseStorage.getInstance();
            /*  theStore.getReferenceFromUrl(thisUser.getUid()).child( thisUser.getUid() ); */
            mstorage.child( thisUser.getUid() ).child("iddoc").child(filePath.getLastPathSegment()).putFile(filePath);
        }
    }
}
