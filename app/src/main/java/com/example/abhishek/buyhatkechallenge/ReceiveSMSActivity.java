package com.example.abhishek.buyhatkechallenge;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gaurav.buyhatkechallenge.R;

import java.util.ArrayList;
import java.util.List;

public class ReceiveSMSActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    CustomReadMessageAdapter customReadMessageAdapter;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recievedsms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.optionsList);
        List<SMSData> smsList = new ArrayList<>();

        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c= getContentResolver().query(uri, null, null ,null,null);
        startManagingCursor(c);

        // Read the sms data and store it in the list
        if(c.moveToFirst()) {
            for(int i=0; i < c.getCount(); i++) {
                SMSData sms = new SMSData();
                sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
                sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
                smsList.add(sms);
                c.moveToNext();
            }
        }
        c.close();
        customReadMessageAdapter = new CustomReadMessageAdapter(this,smsList);
        listView.setAdapter(customReadMessageAdapter);
        listView.setOnItemClickListener(this);


        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReceiveSMSActivity.this,SendSMSActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        SMSData sms = (SMSData)customReadMessageAdapter.getItem(position);
        Intent intent = new Intent(ReceiveSMSActivity.this,MessageDescActivity.class);
        intent.putExtra("SMS_MESSAGE", sms.getBody());
        intent.putExtra("SMS_SENDER", sms.getNumber());
        //Toast.makeText(getApplicationContext(), sms.getBody(), Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"onBackPressed()", Toast.LENGTH_LONG).show();
    }
}
