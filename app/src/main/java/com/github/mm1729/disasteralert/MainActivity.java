package com.github.mm1729.disasteralert;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnHelp;
    Button btnNotify;
    public static String phoneNum = "+19143133452";
    String dangerMsg = "Danger";
    Handler handler;
    double lon = 40.5011191;
    double lat = -74.4441503;
    int bat = 95;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHelp = (Button) findViewById(R.id.btnHelp);
        btnNotify = (Button) findViewById(R.id.btnNotify);


        btnHelp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                sendSMS(phoneNum, dangerMsg);
            }
        });

        btnNotify.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,
                        NotifyActivity.class);
                startActivity(myIntent);
            }
        });

        //Schedule updates
        if(handler == null) {
            handler = new Handler();
            handler.post(runnableCode);
        }

    }

    private void sendSMS(String phoneNum, String message) {
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, DangerActivity.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNum, null, message, pi, null);
    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            String SENT = "SMS_SENT";
            PendingIntent pi= PendingIntent.getBroadcast(MainActivity.this, 0,
                    new Intent(SENT), 0);
            String message = "" + (lon += Math.random()) + "," + (lat += Math.random()) + ","
                    + (bat -= Math.random());
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNum, null, message, pi,null);
            handler.postDelayed(this, 30000);
        }
    };

}
