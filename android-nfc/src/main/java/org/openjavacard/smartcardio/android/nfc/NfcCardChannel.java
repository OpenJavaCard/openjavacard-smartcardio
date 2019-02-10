package org.openjavacard.smartcardio.android.nfc;

import org.openjavacard.smartcardio.generic.GenericCardChannel;

import javax.smartcardio.CardException;
import java.nio.ByteBuffer;

public class NfcCardChannel extends GenericCardChannel {

    public NfcCardChannel(NfcCard card, int channel) {
        super(card, channel);
    }

    @Override
    public int transmit(ByteBuffer command, ByteBuffer response) throws CardException {
        return 0;
    }

    @Override
    public void close() throws CardException {
    }

}
