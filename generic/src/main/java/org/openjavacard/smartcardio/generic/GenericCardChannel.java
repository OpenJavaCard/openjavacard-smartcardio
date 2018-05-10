package org.openjavacard.smartcardio.generic;

import javax.smartcardio.*;
import java.nio.ByteBuffer;

public class GenericCardChannel extends CardChannel {

    private final Card mCard;
    private final int mChannelNumber;

    public GenericCardChannel(Card card, int channel) {
        mCard = card;
        mChannelNumber = channel;
    }

    @Override
    public Card getCard() {
        return mCard;
    }

    @Override
    public int getChannelNumber() {
        return mChannelNumber;
    }

    @Override
    public ResponseAPDU transmit(CommandAPDU command) throws CardException {
        return null;
    }

    @Override
    public int transmit(ByteBuffer command, ByteBuffer response) throws CardException {
        CommandAPDU capdu = new CommandAPDU(command);
        ResponseAPDU rapdu = transmit(capdu);
        byte[] rapduBytes = null;//rapdu.getBytes();
        //response.put(rapduBytes);
        return rapduBytes.length;
    }

    @Override
    public void close() throws CardException {
    }

}
