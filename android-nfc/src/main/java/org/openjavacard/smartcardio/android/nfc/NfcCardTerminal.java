package org.openjavacard.smartcardio.android.nfc;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardNotPresentException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class NfcCardTerminal extends GenericCardTerminal {

    private static final String TAG = NfcCardTerminal.class.getName();

    private static final AtomicInteger ID_COUNTER = new AtomicInteger();

    private NfcCardTerminals mTerminals;
    private Tag mTag;
    private IsoDep mIsoTag;
    private NfcCard mCard;

    NfcCardTerminal(NfcCardTerminals terminals, Tag tag, IsoDep isoTag) {
        super(terminals, "NFC tag #" + ID_COUNTER.getAndIncrement());
        mTerminals = terminals;
        mTag = tag;
        mIsoTag = isoTag;
        mCard = new NfcCard(this, tag, isoTag);
    }

    void checkConnected() throws CardException {
        if(!isCardPresent()) {
            throw new CardNotPresentException("NFC tag disconnected");
        }
    }

    @Override
    public boolean isCardPresent() {
        return mIsoTag.isConnected();
    }

    @Override
    public Card connect(String protocol) throws CardException {
        if(protocol == null) {
            throw new NullPointerException();
        }
        Log.d(TAG, "connect(" + protocol + ")");
        if(protocol.equals("*") || protocol.equals("T=CL")) {
            try {
                mIsoTag.connect();
            } catch (IOException e) {
                throw new CardException("Error connecting to tag", e);
            }
            if(!mIsoTag.isConnected()) {
                throw new CardNotPresentException("NFC tag no longer present");
            }
            mCard.connected("T=CL", null);
            return mCard;
        } else {
            throw new IllegalArgumentException("Protocol " + protocol + " not supported");
        }
    }

}
