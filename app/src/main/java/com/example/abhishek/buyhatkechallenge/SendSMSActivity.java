package com.example.abhishek.buyhatkechallenge;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gaurav.buyhatkechallenge.R;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class SendSMSActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    Button btnSendSMS;
    EditText txtPhoneNo;
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sendsms);
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        txtMessage = (EditText) findViewById(R.id.txtMessage);

        btnSendSMS.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String phoneNo = txtPhoneNo.getText().toString();
                String message = txtMessage.getText().toString();
                if (phoneNo.length()>0 && message.length()>0)
                    sendSMS(phoneNo, message);
                else
                    Toast.makeText(getBaseContext(),
                            "Please enter both phone number and message.",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void sendSMS(String phoneNumber, String message){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms){
        if ( ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_SMS)==PackageManager.PERMISSION_GRANTED) {
            onGranted();
        }
    }

    void onGranted(){
        Toast.makeText(getApplication(),"You have Permission",Toast.LENGTH_LONG).show();

    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.e("errorpermissionDenied",perms.toString());
        if (!perms.isEmpty()) {
            String permission[] = new String[perms.size()];
            for (int i=0;i<perms.size();i++){
                permission[i] = perms.get(i);
            }
            methodRequiresPermission(permission);
            EasyPermissions.checkDeniedPermissionsNeverAskAgain(this, "Please enable these permissions they are critical for app performance",
                    R.string.settings, R.string.cancel, perms);
        }else {
            onGranted();
        }
    }

    private void methodRequiresPermission(String perms1[]) {
        String[] perms = {Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS};
        if (EasyPermissions.hasPermissions(this, perms)) {
            onGranted();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this,"Please allow these permissons to use app",
                    1, perms1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ReceiveSMSActivity.class);
        startActivity(intent);
        finish();
    }

}