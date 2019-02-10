package org.openjavacard.smartcardio.generic;

import javax.smartcardio.*;
import java.nio.ByteBuffer;

public abstract class GenericCardChannel extends CardChannel {

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
        byte[] commandBytes = command.getBytes();
        ByteBuffer commandBuffer = ByteBuffer.wrap(commandBytes);
        ByteBuffer responseBuffer = ByteBuffer.allocate(command.getNe());
        commandBuffer.put(commandBytes);
        int responseLength = transmit(commandBuffer, responseBuffer);
        byte[] responseBytes = new byte[responseLength];
        responseBuffer.get(responseBytes, 0, responseLength);
        return new ResponseAPDU(responseBytes);
    }

    @Override
    public abstract int transmit(ByteBuffer command, ByteBuffer response) throws CardException;

    @Override
    public abstract void close() throws CardException;

}
