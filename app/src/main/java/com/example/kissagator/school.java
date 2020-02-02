package com.example.kissagator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class school extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(school.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.schools));


        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(this);


        // save to internal storage
    }


    public static String schoolChoice = " UF | University of Florida";
    public school (){

        System.out.println(schoolChoice);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        schoolChoice = parent.getItemAtPosition(pos).toString();
        //android.R.layout.simple_list_item_1 = schoolChoice;
        System.out.println(schoolChoice);
    }
    public void onNothingSelected(AdapterView<?> parent) {
        //nothing
        System.out.println("onNothingSelected " + schoolChoice);
    }

    public String getSchoolChoice() {
        System.out.println("getSchoolChoice() " + schoolChoice);
        String bb = "";
        if (schoolChoice.contains("UCF")){
            bb = "UCF";

        } else if (schoolChoice.contains("FSU")){
            bb = "FSU";

        } else if (schoolChoice.contains("USF")){
            bb = "USF";

        } else if (schoolChoice.contains("UNF")){
           bb = "UNF";

        } else if (schoolChoice.contains("UM")){
           bb = "UM";

        } else if (schoolChoice.contains("SF")){

           bb = "SF";
        } else if (schoolChoice.contains("UF")){

           bb = "UF";
        }

        return bb;
    }


}
