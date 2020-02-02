//package com.example.kissagator;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

package com.example.kissagator;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;

//import android.support.v4.app.ActivityCompat;
import androidx.core.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
import androidx.core.content.ContextCompat;
import android.telephony.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.tutorialspoint.R;
import com.example.kissagator.R;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button sendBtn;
    EditText txtphoneNo;
    String phoneNo;
    String message;
    private Button schoolchange;
    private Button contactfromhome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        txtphoneNo = (EditText) findViewById(R.id.editText);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
       // button linking home to page where we change schools
        schoolchange = (Button) findViewById(R.id.schoolchange);
        schoolchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSchool();

            }
        });
        // button linking home to contact screen
        contactfromhome = (Button) findViewById(R.id.contactfromhome);
        contactfromhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts();
            }
        });


    }
    public void openSchool(){
        Intent intent = new Intent(this, school.class);
        startActivity(intent);

    }
    public void openContacts(){
        Intent intenteee = new Intent(this, contacts.class);
        startActivity(intenteee);
    }

    protected void sendSMSMessage() {
        phoneNo = txtphoneNo.getText().toString();
        school a = new school();
        String choosey = a.getSchoolChoice();
        System.out.println(a.getSchoolChoice());

        if (choosey.equals("FSU")){
            message = "A nasty nole has sent you a kiss :)";

        } else if (choosey.equals("USF")){
            message = "A bashful bull has sent you a kiss :)";

        } else if (choosey.equals("UNF")){
            message = "An obliging osprey has sent you a kiss :)";

        } else if (choosey.equals("UM")){
            message = "A hairy hurricane has sent you a kiss :)";

        } else if (choosey.equals("SF")){
            message = "A good doggo has sent you a kiss :)";
        } else {
            message = "A gator has sent you a kiss :)";
        }


        int isAllowed = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if ( isAllowed == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, message, null, null);
                Toast.makeText(getApplicationContext(), "Permission failed, please try again.",
                        Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
                Toast.makeText(getApplicationContext(),
                        "SMS sent.", Toast.LENGTH_LONG).show();
            }
        }

        //ON Successfully sent
        txtphoneNo.getText().clear();
        //Bind eventlisterner
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}