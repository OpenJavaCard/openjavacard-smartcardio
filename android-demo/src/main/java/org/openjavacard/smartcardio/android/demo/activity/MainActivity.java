package org.openjavacard.smartcardio.android.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.openjavacard.smartcardio.android.demo.R;
import org.openjavacard.smartcardio.android.demo.fragment.TerminalInfoFragment;
import org.openjavacard.smartcardio.android.demo.fragment.TerminalListFragment;
import org.openjavacard.smartcardio.android.nfc.SmartcardIOAndroidNfc;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;
import org.openjavacard.smartcardio.generic.GenericCardTerminals;

public class MainActivity extends AppCompatActivity implements GenericCardTerminals.Listener {

    private static final String TAG = MainActivity.class.getName();

    private SmartcardIOAndroidNfc mNfcSC;

    private FragmentManager mFragmentManager;

    private TerminalListFragment mTerminalList;
    private TerminalInfoFragment mTerminalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        // set the layout
        setContentView(R.layout.activity_main);
        // set up action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.activity_main_title);
        // create the list fragment
        mTerminalList = new TerminalListFragment();
        mTerminalInfo = new TerminalInfoFragment();
        mFragmentManager = getSupportFragmentManager();
        // create SCIO interfaces
        mNfcSC = new SmartcardIOAndroidNfc(this);
        mNfcSC.getTerminals().setListener(this);
        // start with the terminal list
        switchToTerminalList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu()");
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected(" + item.getTitle() + ")");
        switch (item.getItemId()) {
            case R.id.action_settings:
                startSettings();
                return true;
            case R.id.action_about:
                startAbout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
        mNfcSC.enable();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause()");
        mNfcSC.disable();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy()");
        mNfcSC = null;
        super.onDestroy();
    }

    private void startSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    private void startAbout() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void switchToTerminalList() {
        mFragmentManager.beginTransaction().replace(R.id.main_layout, mTerminalList).commit();
    }

    public void switchToTerminalInfo(GenericCardTerminal terminal) {
        mTerminalInfo.setTerminal(terminal);
        mFragmentManager.beginTransaction().replace(R.id.main_layout, mTerminalInfo).commit();
    }

    @Override
    public void onTerminalAdded(GenericCardTerminals terminals, GenericCardTerminal terminal) {
        mTerminalList.setTerminals(terminals.getTerminals());
    }

    @Override
    public void onTerminalRemoved(GenericCardTerminals terminals, GenericCardTerminal terminal) {
        mTerminalList.setTerminals(terminals.getTerminals());
    }

}
