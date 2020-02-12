package org.openjavacard.smartcardio.android.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import org.openjavacard.smartcardio.android.demo.R;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = SettingsActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        // set the layout
        setContentView(R.layout.activity_settings);
        // configure the action bar
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null) {
            // enable up-navigation
            actionbar.setDisplayHomeAsUpEnabled(true);
            // set up action bar
            actionbar.setTitle(R.string.activity_settings_title);
        }
    }

}
