package com.github.mm1729.disasteralert;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewDisasterActivity extends Activity {

    Button btnHelp, btnSafe;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdisaster);

        btnHelp = (Button) findViewById(R.id.btnHelp);
        btnSafe = (Button) findViewById(R.id.btnSafe);

        Intent intent = getIntent();
        String message = intent.getStringExtra(SmsReceiver.EXTRA_MESSAGE);
        title = (TextView) findViewById(R.id.title);
        title.setText(message);



        btnSafe.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(NewDisasterActivity.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PendingIntent pi = PendingIntent.getActivity(NewDisasterActivity.this, 0,
                        new Intent(NewDisasterActivity.this, DangerActivity.class), 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(MainActivity.phoneNum, null, "Danger", pi, null);
            }
        });

    }
}