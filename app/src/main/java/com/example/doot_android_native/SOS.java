package com.example.doot_android_native;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SOS extends AppCompatActivity {

    private static final int PERMISSION_RQST_SEND = 0;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_o_s);


        b = (Button) findViewById(R.id.SOSbutton);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSMessage();

                Toast.makeText(SOS.this,"Sent", Toast.LENGTH_LONG).show();
                Intent i = new Intent(SOS.this, Instructions.class);
                startActivity(i);
                finish();
            }
        });
      }

    protected void sendSMSMessage() {
        //        phoneNo = phoneNo.getText().toString(); //we’ll get the phone number from the user
        //        message = myMessage.getText().toString();//we’ll get the Message from the user
        //We’ll check the permission is granted or not . If not we’ll change
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)) {
            }
            else { ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_RQST_SEND);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_RQST_SEND: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("9831872792", null, "message", null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "SMS failed, you may try again later.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
}