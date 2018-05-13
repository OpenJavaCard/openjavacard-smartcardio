package javax.smartcardio;

import java.nio.ByteBuffer;

/**
 * Base class for card channels
 */
public abstract class CardChannel {

    /**
     * Main constructor
     */
    protected CardChannel() {
    }

    /** @return the card this channel is for */
    public abstract Card getCard();

    /** @return the channel number of this channel */
    public abstract int getChannelNumber();

    /**
     * Transmit an APDU through the channel
     * @param command to transmit
     * @return response to command
     * @throws CardException on error
     */
    public abstract ResponseAPDU transmit(CommandAPDU command) throws CardException;

    /**
     * Transmit a raw APDU through the channel
     * @param command buffer with command
     * @param response buffer for response
     * @return length of response
     * @throws CardException on error
     */
    public abstract int transmit(ByteBuffer command, ByteBuffer response)
            throws CardException;

    /**
     * Close the channel
     * @throws CardException on error
     */
    public abstract void close() throws CardException;

}
