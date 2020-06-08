package com.example.ownerinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowOverlay = (Button) findViewById(R.id.show_overlay);
        btnShowOverlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchOverlayService();
                finish();
            }
        });
    }

    private void launchOverlayService() {
        Intent intent = new Intent(MainActivity.this, OverlayService.class);
        startService(intent);
    }
}