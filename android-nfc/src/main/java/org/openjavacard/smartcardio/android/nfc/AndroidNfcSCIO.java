package org.openjavacard.smartcardio.android.nfc;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Handler;
import android.util.Log;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;

import javax.smartcardio.CardException;

public class AndroidNfcSCIO {

    private static final String TAG = AndroidNfcSCIO.class.getName();

    private static final long POLL_INTERVAL = 1000;

    /** Activity that we are using for NFC */
    private final Activity mActivity;
    /** Handler for polling */
    private final Handler mHandler;
    /** Runnable doing the polling */
    private final NfcPollerRunnable mPoller;
    /** Adapter in use */
    private NfcAdapter mAdapter;
    /** SmartcardIO terminals object */
    private NfcCardTerminals mTerminals;

    public AndroidNfcSCIO(Activity activity) {
        mActivity = activity;
        mHandler = new Handler(activity.getMainLooper());
        mPoller = new NfcPollerRunnable();
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
        Log.d(TAG, "enable()");
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
        Log.d(TAG, "disable()");
        if(mAdapter != null) {
            mAdapter.disableReaderMode(mActivity);
            mAdapter = null;
        }
        mHandler.removeCallbacks(mPoller);
    }

    private class NfcPollerRunnable implements Runnable {
        @Override
        public void run() {
            Log.v(TAG, "poll()");
            for(GenericCardTerminal terminal: mTerminals.getTerminals()) {
                try {
                    if(!terminal.isCardPresent()) {
                        mTerminals.removeTerminal(terminal);
                    }
                } catch (CardException e) {
                    Log.e(TAG, "Error polling for card", e);
                }
            }
            mHandler.postDelayed(this, POLL_INTERVAL);
        }
    }

    private class NfcReaderCallback implements NfcAdapter.ReaderCallback {
        @Override
        public void onTagDiscovered(Tag tag) {
            Log.d(TAG, "onTagDiscovered()");
            IsoDep tagIso = IsoDep.get(tag);
            if(tagIso != null) {
                mTerminals.newTag(tag, tagIso);
            }
        }
    }

}
