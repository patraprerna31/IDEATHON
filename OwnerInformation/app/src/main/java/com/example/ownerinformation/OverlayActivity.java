package com.example.ownerinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class OverlayActivity extends Activity {

    private static final String TAG = OverlayActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d( TAG, "[onCreate]" );
        super.onCreate( savedInstanceState );

        Window window = getWindow();
        window.addFlags( WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );

        // fragment表示
        Fragment fragment = getFragmentManager().findFragmentByTag( FragmentType.OVERLAY.getTag() );
        if (fragment == null) {
            fragment = OverlayFragment.newInstance();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace( android.R.id.content, fragment, FragmentType.OVERLAY.getTag() );
        ft.commit();
    }

    private enum FragmentType {
        OVERLAY( "overlay" );
        private String tag;

        private FragmentType(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }
}