package org.openjavacard.smartcardio.android.omapi;

import android.annotation.TargetApi;
import android.content.Context;
import android.se.omapi.SEService;
import android.util.Log;

import java.util.concurrent.Executor;

@TargetApi(28)
public class AndroidOmapiSCIO {

    private static final String TAG = AndroidOmapiSCIO.class.getName();

    private static final long CONNECT_TIMEOUT_MSEC = 3000;

    private Context mContext;

    private SEService mService;
    private boolean mConnected;

    private OmapiCardTerminals mTerminals;

    private Executor mConnectExecutor;
    private ConnectListener mConnectListener;

    public AndroidOmapiSCIO(Context context) {
        mContext = context;
        mTerminals = new OmapiCardTerminals();
        mConnectExecutor = new SynchronousExecutor();
        mConnectListener = new ConnectListener();
    }

    public void enable() {
        Log.i(TAG, "enable()");
        // connect to service
        mService = new SEService(mContext, mConnectExecutor, mConnectListener);
    }

    public void disable() {
        Log.i(TAG, "disable()");
        // reset connect state
        mConnected = false;
        // disconnect from service
        if(mService != null) {
            if(mService.isConnected()) {
                mService.shutdown();
            }
            mService = null;
        }
    }

    private void onServiceConnected() {
        mConnected = true;
    }

    private class SynchronousExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    private class ConnectListener implements SEService.OnConnectedListener {
        @Override
        public void onConnected() {
            onServiceConnected();
        }
    }

}
