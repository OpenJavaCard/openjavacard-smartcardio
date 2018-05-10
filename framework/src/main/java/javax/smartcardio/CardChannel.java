package javax.smartcardio;

import java.nio.ByteBuffer;

public abstract class CardChannel {

    protected CardChannel() {
    }

    public abstract Card getCard();

    public abstract int getChannelNumber();

    public abstract ResponseAPDU transmit(CommandAPDU command) throws CardException;

    public abstract int transmit(ByteBuffer command, ByteBuffer response)
            throws CardException;

    public abstract void close() throws CardException;

}
