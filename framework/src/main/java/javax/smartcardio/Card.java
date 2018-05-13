package javax.smartcardio;

/**
 * Base class for interfacing to smart cards
 */
public abstract class Card {

    /**
     * Main constructor
     */
    protected Card() {
    }

    /** @return the ATR sent by the card */
    public abstract ATR getATR();

    /** @return the protocol used to communicate with the card */
    public abstract String getProtocol();

    /** @return the basic channel for the card */
    public abstract CardChannel getBasicChannel();

    /**
     * Open a logical channel
     * @return the channel
     * @throws CardException on error
     */
    public abstract CardChannel openLogicalChannel() throws CardException;

    /**
     * Lock the card for thread-exclusive access
     * @throws CardException on error
     */
    public abstract void beginExclusive() throws CardException;

    /**
     * End thread-exclusive access
     * @throws CardException on error
     */
    public abstract void endExclusive() throws CardException;

    /**
     * Transmit a control command
     * @param controlCode
     * @param command
     * @return
     * @throws CardException
     */
    public abstract byte[] transmitControlCommand(int controlCode,
                                                  byte[] command) throws CardException;

    /**
     * Disconnect from the card
     * @param reset true to reset the card
     * @throws CardException on error
     */
    public abstract void disconnect(boolean reset) throws CardException;

}
