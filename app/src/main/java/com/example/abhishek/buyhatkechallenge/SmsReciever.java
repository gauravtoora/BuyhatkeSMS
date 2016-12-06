package com.example.abhishek.buyhatkechallenge;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;

import com.example.gaurav.buyhatkechallenge.R;

/**
 * Created by gaurav on 5/12/16.
 */
public class SmsReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] smsm = null;
            String sms_str = "";
            String sender = "";
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                smsm = new SmsMessage[pdus.length];
                int id = 1;
                for (int i = 0; i < 1; i++) {
                    smsm[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sms_str += "Sent From: " + smsm[i].getOriginatingAddress();
                    sender = smsm[i].getDisplayOriginatingAddress();

                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                            .setContentTitle(sender)
                            .setContentText(sms_str)
                            .setSmallIcon(R.drawable.qwqw);
                    Intent in = new Intent (context, ReceiveSMSActivity.class);
                    in.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    PendingIntent pend = PendingIntent.getActivity(context, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationManager notificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationBuilder.setContentIntent (pend);
                    try{
                    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
                    }catch(Exception e){
                        e.printStackTrace();
                        e.toString();
                    }
                }
            }
        }
    }


}
