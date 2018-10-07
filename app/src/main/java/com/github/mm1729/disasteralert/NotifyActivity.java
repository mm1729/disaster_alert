package com.github.mm1729.disasteralert;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NotifyActivity extends Activity {
    Button btnCancel, btnSend;
    Spinner service;
    EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSend = (Button) findViewById(R.id.btnSend);


        btnCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(NotifyActivity.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String message = "";
                service = (Spinner) findViewById(R.id.service_spinner);
                String sel_service = String.valueOf(service.getSelectedItem());
                msg = (EditText) findViewById(R.id.notify_msg);
                String send_msg = msg.getText().toString();

                message = "{service:'"+sel_service+"', message:'" + send_msg + "'}";
                PendingIntent pi = PendingIntent.getActivity(NotifyActivity.this, 0,
                        new Intent(NotifyActivity.this, MainActivity.class), 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(MainActivity.phoneNum, null, message, pi, null);
            }
        });
    }
}
