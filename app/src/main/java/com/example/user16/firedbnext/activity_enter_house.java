package com.example.user16.firedbnext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.*;

import java.util.ArrayList;
import java.util.List;


public class activity_enter_house extends AppCompatActivity {

    FirebaseUser thisUser;
    private DatabaseReference housesDB;
    /*  global variables for the input components */
    Spinner countryVal, provinceVal, cityVal, yearsBVal;/*       */
    RadioButton buildTVal, buildTVal_O;
    RadioGroup rgType;
    EditText myAddress, postCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_house);

        /*  intitalise the variables declared  */
        thisUser = FirebaseAuth.getInstance().getCurrentUser();
        housesDB = FirebaseDatabase.getInstance().getReference().child(thisUser.getUid()).child("House");

        /*  loading values for the country drop-down menu */
        myAddress = (EditText) findViewById(R.id.evStAdd) ;
        postCode = (EditText) findViewById(R.id.evPostalCode);

        countryVal = (Spinner) findViewById(R.id.evCountry);
        countryVal.setOnItemSelectedListener(new MyOnItemSelectedListener());

       /*  List<String> list = new ArrayList<>(R.array.countries);
        ArrayAdapter<String> ccAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list );
        ArrayAdapter<CharSequence> cAdapter = ArrayAdapter.createFromResource(this, R.array.countries,R.layout.support_simple_spinner_dropdown_item  );
        cAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        countryVal.setAdapter(cAdapter); */


        /*  what is left for you is to implement the on-click for this dropdown menu */

        /*  loading values for the province drop-down menu */

        provinceVal = (Spinner) findViewById(R.id.evProvince);
        ArrayAdapter<CharSequence> pAdapter = ArrayAdapter.createFromResource(this, R.array.provinces,R.layout.support_simple_spinner_dropdown_item  );
        pAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        provinceVal.setAdapter(pAdapter);

        provinceVal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String aPro = getIndex(provinceVal);

                /*  for the city please  */
                cityVal = (Spinner) findViewById(R.id.evTown);

                Toast.makeText(getApplicationContext(), "Province Selected Is " + Integer.toString(position) + ", Name is: " + aPro, Toast.LENGTH_LONG).show();
                switch (position )
                {
                    case 0:
                        ArrayAdapter<CharSequence> cAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.gauteng_towns,R.layout.support_simple_spinner_dropdown_item  );
                        cAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cityVal.setAdapter(cAdapter);
                        break;

                    case 1:
                        ArrayAdapter<CharSequence> kAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.kzn_towns,R.layout. support_simple_spinner_dropdown_item  );
                        kAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cityVal.setAdapter(kAdapter);
                        break;

                    case 2:
                        ArrayAdapter<CharSequence> lAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.limpopo_towns,R.layout.support_simple_spinner_dropdown_item  );
                        lAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cityVal.setAdapter(lAdapter);
                        break;

                    case 3:
                        ArrayAdapter<CharSequence> wAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.wcape_towns,R.layout.support_simple_spinner_dropdown_item  );
                        wAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cityVal.setAdapter(wAdapter);
                        break;
                }

            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*  loading values for the city drop-down menu */



         /*  what is left for you is to implement the on-click for this dropdown menu */


        yearsBVal = (Spinner) findViewById(R.id.evYearsBuilt);
        /* yearsBVal.setSelection(getIndex(mySpin, myValue)); */


        /*  implementing the radio buttons - NB: this two should share an onclick event  */
        buildTVal = (RadioButton) findViewById(R.id.typeBusiness);
        buildTVal_O = (RadioButton) findViewById(R.id.typeHome);
        rgType = (RadioGroup) findViewById(R.id.evBuildingType);
    }

    public String getIndex(Spinner aSPin)
    {

        String aValue = aSPin.getSelectedItem().toString();
        return aValue;
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            String selectedItem = parent.getItemAtPosition(pos).toString();
            String selectedProvince = "";
            //check which spinner triggered the listener
            switch (parent.getId()) {
                //country spinner
                case R.id.evProvince:
                    //make sure the country was already selected during the onCreate
                    if(selectedProvince != null){
                        Toast.makeText(parent.getContext(), "Country you selected is " + selectedItem,
                                Toast.LENGTH_LONG).show();
                    }
                    selectedProvince = selectedItem;
                    break;
                // spinner
                /* case R.id.spinner1:
                    //make sure the animal was already selected during the onCreate
                    if(selectedAnimal != null){
                        Toast.makeText(parent.getContext(), "Animal selected is " + selectedItem,
                                Toast.LENGTH_LONG).show();
                    }
                    selectedAnimal = selectedItem;
                    break; */
            }


        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }

    public void buildTy(View view)
    {
        /*  buildTVal, buildTVal_O */
        int slt = rgType.getCheckedRadioButtonId();
        RadioButton rdb = (RadioButton) findViewById(slt);
        /* buildTVal_O.toggle(); */
        String bType  = ( (RadioButton) this.findViewById(rgType.getCheckedRadioButtonId())) .getText().toString();  /* rdb.getText().toString() ;*/
        Toast.makeText(this, "Value Selected Is:  " + bType, Toast.LENGTH_SHORT).show();

    }

    public void regHouse(View view) {
        String works = getIndex(provinceVal);
        Toast.makeText(this, "Province Selected Is " + works,
                Toast.LENGTH_LONG).show();

        /*  here then i want you to register the house details into your database */

        /*  using the house the class object: */
        House aHouse = new House();
        /* aHouse.setHouse("34 Rosetta Part Str","South Africa", "Western Cape", "Stellenbosch",   500, 5); */
        String aa = countryVal.getSelectedItem().toString() ;
        String bb = provinceVal .getSelectedItem().toString();
        String cc = cityVal.getSelectedItem().toString();
        String dd = myAddress.getText().toString();
        int ee = Integer.valueOf( postCode.getText().toString());
        int ff =  Integer.valueOf( yearsBVal.getSelectedItem().toString() );

        /*  using the selected values for the functionality and initialisation of the object */
        /*  setHouse(String address, String country, String province, String city, int postCode, int years) */
        aHouse.setHouse(dd, aa, bb, cc, ee, ff);
        /* housesDB.child("House").setValue(aHouse); */
        housesDB.getKey();
        if ( housesDB.push().setValue(aHouse) .isSuccessful())
        {
            myAddress.setText("");
            postCode.setText("");
            /* myAddress.setText("");  */
            countryVal.setSelection(0);
            provinceVal.setSelection(0);
            cityVal.setSelection(0);
            countryVal.setSelection(0);

            Toast.makeText(getApplicationContext(), "House Successfully Registered", Toast.LENGTH_SHORT).show();
        }
        // housesDB.push().setValue(aHouse); /* .push() */

        Intent intent = new Intent(activity_enter_house.this, LeonJay.class);
        startActivity(intent);


    }
}
