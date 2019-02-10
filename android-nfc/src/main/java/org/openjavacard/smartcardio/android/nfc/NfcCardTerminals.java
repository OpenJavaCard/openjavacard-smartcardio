package org.openjavacard.smartcardio.android.nfc;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;
import org.openjavacard.smartcardio.generic.GenericCardTerminals;

import java.io.IOException;

public class NfcCardTerminals extends GenericCardTerminals {

    private static final String TAG = NfcCardTerminals.class.getName();

    void newTag(Tag tag, IsoDep isoTag) {
        Log.i(TAG, "newTag()");
        try {
            isoTag.connect();
            addTerminal(new NfcCardTerminal(this, tag, isoTag));
        } catch (IOException e) {
            Log.e(TAG, "Could not connect to tag", e);
        }
    }

}
