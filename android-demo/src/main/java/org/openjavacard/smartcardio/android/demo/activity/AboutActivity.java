package org.openjavacard.smartcardio.android.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import org.openjavacard.smartcardio.android.demo.R;

public class AboutActivity extends AppCompatActivity {

    private static final String TAG = AboutActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        // set the layout
        setContentView(R.layout.activity_about);
    }

}
