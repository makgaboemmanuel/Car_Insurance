package com.example.user16.firedbnext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CarCreate extends AppCompatActivity {

    FirebaseUser thisUser;
    private DatabaseReference carsDB;

    EditText cDistacen, cYears, cColor, cIniVal, cNumPlate;
    Spinner cBrand, cModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_create);

        thisUser = FirebaseAuth.getInstance().getCurrentUser();
        carsDB = FirebaseDatabase.getInstance().getReference().child(thisUser.getUid()).child("Car");

        cDistacen = (EditText) findViewById(R.id.evDistance);
        cYears = (EditText) findViewById(R.id.evYears) ;
        cColor  = (EditText) findViewById(R.id.evColor);
        cIniVal = (EditText) findViewById(R.id.evOnePrice);
        cNumPlate = (EditText) findViewById(R.id.evNumPlate);
        cBrand= (Spinner) findViewById(R.id.evBrand);
        cModel  = (Spinner)  findViewById(R.id.evModel);

        ArrayAdapter<CharSequence> brAdapter = ArrayAdapter.createFromResource(this, R.array.car_brands,R.layout.support_simple_spinner_dropdown_item  );
        brAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cBrand.setAdapter(brAdapter);

        cBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        ArrayAdapter<CharSequence> frAdapter = ArrayAdapter.createFromResource(CarCreate.this, R.array.ford_models,R.layout.support_simple_spinner_dropdown_item  );
                        frAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cModel.setAdapter(frAdapter);

                        break;
                    case 1:
                        ArrayAdapter<CharSequence> hyAdapter = ArrayAdapter.createFromResource(CarCreate.this, R.array.hyundai_models,R.layout.support_simple_spinner_dropdown_item  );
                        hyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cModel.setAdapter(hyAdapter);
                        break;

                    case 2:
                        ArrayAdapter<CharSequence> miAdapter = ArrayAdapter.createFromResource(CarCreate.this, R.array.mitsubishi_models,R.layout.support_simple_spinner_dropdown_item  );
                        miAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cModel.setAdapter(miAdapter);
                        break;

                    case 3:
                        ArrayAdapter<CharSequence> niAdapter = ArrayAdapter.createFromResource(CarCreate.this, R.array.nissan_models,R.layout.support_simple_spinner_dropdown_item  );
                        niAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cModel.setAdapter(niAdapter);

                        break;

                    case 4:
                        ArrayAdapter<CharSequence> toAdapter = ArrayAdapter.createFromResource(CarCreate.this, R.array.toyota_models,R.layout.support_simple_spinner_dropdown_item  );
                        toAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cModel.setAdapter(toAdapter);
                        break;

                    case 5:
                        ArrayAdapter<CharSequence> vwAdapter = ArrayAdapter.createFromResource(CarCreate.this, R.array.volkswagen_models,R.layout.support_simple_spinner_dropdown_item  );
                        vwAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        cModel.setAdapter(vwAdapter);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void regCar(View view)
    {
        String brand = cBrand.getSelectedItem().toString();
        String model = cModel.getSelectedItem().toString();

        int dbcont = 0;
        /*  validate this four please  */
        String color = "";
        if ( TextUtils.isEmpty(cColor.getText().toString()) || cColor.getText().toString().length() < 3)
        { cColor.setError("Please Provide Color");   }
        else { dbcont++;  color = cColor.getText().toString(); }

        double intiPrice = 0.0;
        if (TextUtils.isEmpty( cIniVal.getText().toString()) || cIniVal.getText().toString().length() < 6)
        {  cIniVal.setError("No Car Is < 10 000");}
        else
        { dbcont++; intiPrice = Double.parseDouble(cIniVal.getText().toString());  }

        float distance = 0;
        if (TextUtils.isEmpty( cDistacen.getText().toString() ) || cDistacen.getText().toString().length() < 2)
        { cDistacen.setError("Invalid Distance"); }
        else { dbcont++; distance = Float.parseFloat( cDistacen.getText().toString());  }

        int years = 0;

        if (  TextUtils.isEmpty(cYears.getText().toString()) || Integer.valueOf( cYears.getText().toString()) > 5)
        { cYears.setError("No Cars Older Than 5 Years"); }
        else { dbcont++; years =  Integer.valueOf( cYears.getText().toString());}

        String theNumPlate = "";
        if ( TextUtils.isEmpty( cNumPlate.getText().toString()) || cNumPlate.getText().toString().length() < 4  )
        {   cNumPlate.setError("Invalid Number Plate");  }
        else { dbcont++;   theNumPlate = cNumPlate.getText().toString() ; }

        Car thisCar = new Car();

        thisCar.setCar(intiPrice, brand, model, color, years, distance, theNumPlate);


        carsDB.getKey();

        if ( dbcont == 5  )
        {   carsDB.push().setValue(thisCar);
            Toast.makeText(getApplicationContext(), "Car Successfully Registered", Toast.LENGTH_SHORT).show();
            Intent mainLeo = new Intent(CarCreate.this, LeonJay.class);
            startActivity(mainLeo);
        }

        else { Toast.makeText(getApplicationContext(), "Can't Register Car, Invalid Data, ", Toast.LENGTH_SHORT).show(); }
    }

}
