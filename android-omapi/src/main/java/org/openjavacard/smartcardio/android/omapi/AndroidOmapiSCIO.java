package org.openjavacard.smartcardio.android.omapi;

import android.annotation.TargetApi;
import android.content.Context;
import android.se.omapi.Reader;
import android.se.omapi.SEService;
import android.util.Log;

import java.util.concurrent.Executor;

@TargetApi(28)
public class AndroidOmapiSCIO {

    private static final String TAG = AndroidOmapiSCIO.class.getName();

    /** Context we are running in */
    private Context mContext;
    /** Reference to SE service */
    private SEService mService;
    /** True if connected to SE service */
    private boolean mConnected;
    /** Terminals object */
    private OmapiCardTerminals mTerminals;
    /** Executor for SE service callbacks */
    private Executor mConnectExecutor;
    /** Listener for connect events */
    private ConnectListener mConnectListener;

    /**
     * Main constructor
     * @param context to use
     */
    public AndroidOmapiSCIO(Context context) {
        mContext = context;
        mTerminals = new OmapiCardTerminals();
        mConnectExecutor = new SynchronousExecutor();
        mConnectListener = new ConnectListener();
    }

    /**
     * Get the Terminals object
     * @return the Terminals object
     */
    public OmapiCardTerminals getTerminals() {
        return mTerminals;
    }

    /**
     * @return
     */
    public boolean isEnabled() {
        return mService != null;
    }

    /**
     * Enable the interface
     */
    public void enable() {
        Log.d(TAG, "enable()");
        // connect to service
        mService = new SEService(mContext, mConnectExecutor, mConnectListener);
    }

    /**
     * Disable the interface
     */
    public void disable() {
        Log.d(TAG, "disable()");
        // disconnect from service
        if(mService != null) {
            if(mService.isConnected()) {
                mService.shutdown();
            }
            mService = null;
        }
        // reset connect state
        mConnected = false;
    }

    /**
     * Service connected callback
     */
    private void onServiceConnected() {
        Log.d(TAG, "onServiceConnected()");
        // we are now connected
        mConnected = true;
        // add available readers
        Reader[] readers = mService.getReaders();
        for (Reader reader : readers) {
            mTerminals.addTerminal(new OmapiCardTerminal(mTerminals, reader));
        }
    }

    /**
     * Dummy synchronous executor
     */
    private class SynchronousExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    /**
     * Connect event listener
     */
    private class ConnectListener implements SEService.OnConnectedListener {
        @Override
        public void onConnected() {
            onServiceConnected();
        }
    }

}
