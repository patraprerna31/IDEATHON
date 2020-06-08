package com.example.myapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
  final Context context = this;
  private String mTextViewPercentage;
  //private TextView mTextViewPercentage;
  private Context mContext;
  private int mProgressStatus = 0;
  private String flag;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
    int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    String battery = Integer.toString(batLevel);
    //mTextViewPercentage.setText("" + mProgressStatus + "%");
    flag="event";
    //msg1=mTextViewPercentage.getText().toString();

    // mTextViewPercentage = (TextView) findViewById(R.id.tv_percentage);
    ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.RECEIVE_SMS,Manifest.permission.SEND_SMS},
            10);




    Bundle extras = getIntent().getExtras();
    if(extras!=null) {
      String num = extras.getString("num");
      String msg = extras.getString("msg");
      String flag = extras.getString("flag");

      int duration = Toast.LENGTH_LONG;
      Toast toast = Toast.makeText(context, "I AM At Receiver\nsenderNum: " +num+ ", message: " + msg, duration);
      toast.show();
      //Log.i("TAG", "hihi" );

      SmsManager sms = SmsManager.getDefault();

      //Log.i("TAG", msg1);
      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
      // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
      // PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

      // Setting Dialog Title
      alertDialogBuilder.setTitle("Device Ringing");

      // Setting Dialog Message
      alertDialogBuilder.setMessage("Sender : " + num + "\n" + "Message : " + msg);

      // create alert dialog

      AlertDialog alertDialog = alertDialogBuilder.create();
      //show dialog
      alertDialog.show();
      if(flag.equals("event")){
        sms.sendTextMessage(num, null,battery, null, null);

        flag="eventdone";
        // pi=ni
      }

    }
  }

  @Override

  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == 10) {
      // YES!!
      Log.i("TAG", "MY_PERMISSIONS_REQUEST--> YES");
    }
  }


}