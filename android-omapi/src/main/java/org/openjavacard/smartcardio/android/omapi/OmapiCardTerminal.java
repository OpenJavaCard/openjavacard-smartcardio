package org.openjavacard.smartcardio.android.omapi;

import android.annotation.TargetApi;
import android.se.omapi.Reader;
import android.se.omapi.Session;
import android.util.Log;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;
import org.openjavacard.smartcardio.generic.GenericCardTerminals;

import javax.smartcardio.CardException;
import java.io.IOException;

public class OmapiCardTerminal extends GenericCardTerminal {

    private static final String TAG = OmapiCardTerminal.class.getName();

    /** OMAPI reader object */
    private Reader mReader;

    @TargetApi(28)
    OmapiCardTerminal(GenericCardTerminals terminals, Reader reader) {
        super(terminals, reader.getName());
        mReader = reader;
    }

    @Override
    @TargetApi(28)
    public boolean isCardPresent() {
        return mReader.isSecureElementPresent();
    }

    @Override
    @TargetApi(28)
    public OmapiCard connect(String protocol) throws CardException {
        OmapiCard card;
        if(protocol == null) {
            throw new NullPointerException();
        }
        Log.d(TAG, "connect(" + protocol + ")");
        if(protocol.equals("*")) {
            try {
                // open session with reader
                Session session = mReader.openSession();
                // create and return card object
                card = new OmapiCard(this, session);
                // prepare the object
                card.connected("T?", null);
            } catch (IOException e) {
                throw new CardException("Could not connect to SE " + mReader.getName(), e);
            }
        } else {
            throw new IllegalArgumentException("Protocol " + protocol + " not supported");
        }
        return card;
    }

}
