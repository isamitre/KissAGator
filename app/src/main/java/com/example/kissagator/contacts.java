package com.example.kissagator;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class contacts extends AppCompatActivity {

    MainActivity sender = new MainActivity();

    ListView l1;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        l1 = (ListView)findViewById(R.id.listView);

        btn = (Button) findViewById(R.id.getContacts);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get(v);
            }
        });
    }


    public void get (View v) {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        startManagingCursor(cursor);
        cursor.moveToNext();
        final String[] from = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID};
        int[] to = {android.R.id.text1,android.R.id.text2};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to);
        l1.setAdapter(simpleCursorAdapter);

        l1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        l1.setTextFilterEnabled(true);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0;i<=id;i++){
                    System.exit(1);
                }
            }
        });



    }

    public void contactedPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(contacts.this);
        builder.setTitle("Send a Kiss?");
        builder.setMessage("Are you sure you want to send a kiss to this homie?");
    }

}