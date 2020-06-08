package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

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


            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, "I AM At Receiver\nsenderNum: " +num+ ", message: " + msg, duration);
            toast.show();
            //Log.i("TAG", "hihi" );

                AudioManager audioManager= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            int max_volume_level = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);

            audioManager.setStreamVolume(
                    AudioManager.STREAM_RING,
                    max_volume_level,
                    AudioManager.FLAG_SHOW_UI
            );

            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();



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

    private boolean IsVibrate()
    {
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

        if(audioManager.getRingerMode()==AudioManager.RINGER_MODE_VIBRATE )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean IsRingerSilent()
    {
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

        if(audioManager.getRingerMode()==AudioManager.RINGER_MODE_SILENT )
        {
            return true;
        }
        else
        {
            return false;
        }
    }



}