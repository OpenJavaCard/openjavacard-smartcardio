package org.openjavacard.smartcardio.android.nfc;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import org.openjavacard.smartcardio.generic.GenericCard;
import org.openjavacard.smartcardio.generic.GenericCardChannel;

import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;

public class NfcCard extends GenericCard {

    private NfcCardTerminal mTerminal;
    private Tag mTag;
    private IsoDep mIsoTag;
    private GenericCardChannel mBasicChannel;

    NfcCard(NfcCardTerminal terminal, Tag tag, IsoDep isoTag) {
        super(terminal);
        mTerminal = terminal;
        mTag = tag;
        mIsoTag = isoTag;
        mBasicChannel = new NfcCardChannel(this, 0);
    }

    @Override
    public CardChannel getBasicChannel() {
        return mBasicChannel;
    }

    @Override
    public void beginExclusive() throws CardException {
        mTerminal.checkConnected();
        super.beginExclusive();
    }

    @Override
    public CardChannel openLogicalChannel() throws CardException {
        mTerminal.checkConnected();
        return null;
    }

    @Override
    public void disconnect(boolean reset) throws CardException {
        if(reset) {
            throw new UnsupportedOperationException("Explicit card reset is not supported on NFC");
        }
    }

}
