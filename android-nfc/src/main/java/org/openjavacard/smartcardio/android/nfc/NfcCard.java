package org.openjavacard.smartcardio.android.nfc;

import android.nfc.tech.IsoDep;
import org.openjavacard.smartcardio.generic.GenericCardChannel;

import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;

public class NfcCard extends Card {

    private NfcCardTerminal mTerminal;
    private IsoDep mIsoTag;
    private GenericCardChannel mBasicChannel;

    NfcCard(NfcCardTerminal terminal, IsoDep isoTag) {
        mTerminal = terminal;
        mIsoTag = isoTag;
        mBasicChannel = new GenericCardChannel(this, 0);
    }

    @Override
    public ATR getATR() {
        return null;
    }

    @Override
    public String getProtocol() {
        return "T=CL";
    }

    @Override
    public CardChannel getBasicChannel() {
        return mBasicChannel;
    }

    @Override
    public void beginExclusive() throws CardException {
        mTerminal.checkConnected();
    }

    @Override
    public void endExclusive() throws CardException {
    }

    @Override
    public CardChannel openLogicalChannel() throws CardException {
        mTerminal.checkConnected();
        return null;
    }

    @Override
    public byte[] transmitControlCommand(int controlCode, byte[] command) throws CardException {
        throw new UnsupportedOperationException("Control commands not supported");
    }

    @Override
    public void disconnect(boolean reset) throws CardException {
        if(reset) {
            throw new UnsupportedOperationException("Explicit card reset is not supported");
        }
    }

}
