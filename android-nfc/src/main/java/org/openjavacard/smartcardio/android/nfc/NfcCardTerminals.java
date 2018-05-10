package org.openjavacard.smartcardio.android.nfc;

import android.nfc.tech.IsoDep;
import android.util.Log;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;
import org.openjavacard.smartcardio.generic.GenericCardTerminals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NfcCardTerminals extends GenericCardTerminals {

    private static final String TAG = NfcCardTerminals.class.getName();

    NfcCardTerminals() {
    }

    void newTag(IsoDep isoTag) {
        Log.i(TAG, "newTag()");
        try {
            isoTag.connect();
            addTerminal(new NfcCardTerminal(this, isoTag));
        } catch (IOException e) {
            Log.e(TAG, "Could not connect to tag", e);
        }
    }

}
