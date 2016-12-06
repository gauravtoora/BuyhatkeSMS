package com.example.abhishek.buyhatkechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.gaurav.buyhatkechallenge.R;

/**
 * Created by gaurav on 6/12/16.
 */
public class MessageDescActivity extends AppCompatActivity{

    TextView sender, message;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_desc);
        message = (TextView)findViewById(R.id.tv_message);
        sender = (TextView)findViewById(R.id.tv_sender);
        String str1 = getIntent().getStringExtra("SMS_MESSAGE");
        String str2 = getIntent().getStringExtra("SMS_SENDER");
        message.setText(str1);
        sender.setText(str2);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(this, ReceiveSMSActivity.class);
        startActivity(intent);
        finish();

    }
}
