package org.openjavacard.smartcardio.android.nfc;

import android.nfc.tech.IsoDep;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardNotPresentException;
import javax.smartcardio.CardTerminal;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class NfcCardTerminal extends GenericCardTerminal {

    private static final Logger LOG = LoggerFactory.getLogger(NfcCardTerminal.class);

    private static final AtomicInteger ID_COUNTER = new AtomicInteger();

    private NfcCardTerminals mTerminals;
    private IsoDep mIsoTag;
    private NfcCard mCard;

    NfcCardTerminal(NfcCardTerminals terminals, IsoDep isoTag) {
        super(terminals, "NFC tag #" + ID_COUNTER.getAndIncrement());
        mTerminals = terminals;
        mIsoTag = isoTag;
        mCard = new NfcCard(this, isoTag);
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
        LOG.info("connect(" + protocol + ")");
        if(protocol.equals("*") || protocol.equals("T=CL")) {
            try {
                mIsoTag.connect();
            } catch (IOException e) {
                throw new CardException("Error connecting to tag", e);
            }
            if(!mIsoTag.isConnected()) {
                throw new CardNotPresentException("NFC tag no longer present");
            }
            return mCard;
        } else {
            throw new IllegalArgumentException("Protocol " + protocol + " not supported");
        }
    }

}
