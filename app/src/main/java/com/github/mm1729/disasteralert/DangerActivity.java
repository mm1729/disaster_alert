package com.github.mm1729.disasteralert;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DangerActivity extends Activity {

    Button btnSafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);


        btnSafe = (Button) findViewById(R.id.btnSafe);


        btnSafe.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(DangerActivity.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        });
    }


}
