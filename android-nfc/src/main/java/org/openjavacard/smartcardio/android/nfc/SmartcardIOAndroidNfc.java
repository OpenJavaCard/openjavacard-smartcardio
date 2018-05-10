package org.openjavacard.smartcardio.android.nfc;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Handler;
import android.util.Log;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.smartcardio.CardException;

public class SmartcardIOAndroidNfc {

    private static final String TAG = SmartcardIOAndroidNfc.class.getName();

    private static final long POLL_INTERVAL = 1000;

    /** Activity that we are using for NFC */
    private final Activity mActivity;
    /** Handler for polling */
    private final Handler mHandler;
    /** Runnable doing the polling */
    private final Runnable mPoller;
    /** Adapter in use */
    private NfcAdapter mAdapter;
    /** SmartcardIO terminals object */
    private NfcCardTerminals mTerminals;


    public SmartcardIOAndroidNfc(Activity activity) {
        mActivity = activity;
        mHandler = new Handler(activity.getMainLooper());
        mPoller = new Runnable() {
            @Override
            public void run() {
                poll();
                mHandler.postDelayed(this, POLL_INTERVAL);
            }
        };
        mAdapter = null;
        mTerminals = new NfcCardTerminals();
    }

    public boolean isNfcSupported() {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(mActivity);
        return adapter != null;
    }

    public boolean isNfcEnabled() {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(mActivity);
        return adapter != null && adapter.isEnabled();
    }

    public boolean isEnabled() {
        return mAdapter != null;
    }

    public NfcCardTerminals getTerminals() {
        return mTerminals;
    }

    public void enable() {
        Log.i(TAG, "enable()");
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(mActivity);
        int flags =
                NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK
                | NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS
                | NfcAdapter.FLAG_READER_NFC_A;
        adapter.enableReaderMode(mActivity, new NfcReaderCallback(), flags, null);
        mAdapter = adapter;
        mHandler.postDelayed(mPoller, 1000);
    }

    public void disable() {
        Log.i(TAG, "disable()");
        if(mAdapter != null) {
            mAdapter.disableReaderMode(mActivity);
            mAdapter = null;
        }
        mHandler.removeCallbacks(mPoller);
    }

    private void poll() {
        Log.i(TAG, "poll()");
        for(GenericCardTerminal terminal: mTerminals.getTerminals()) {
            try {
                if(!terminal.isCardPresent()) {
                    mTerminals.removeTerminal(terminal);
                }
            } catch (CardException e) {
                Log.e(TAG, "Error polling for card", e);
            }
        }
    }

    private class NfcReaderCallback implements NfcAdapter.ReaderCallback {
        @Override
        public void onTagDiscovered(Tag tag) {
            Log.i(TAG, "onTagDiscovered()");
            IsoDep tagIso = IsoDep.get(tag);
            if(tagIso != null) {
                mTerminals.newTag(tagIso);
            }
        }
    }

}
