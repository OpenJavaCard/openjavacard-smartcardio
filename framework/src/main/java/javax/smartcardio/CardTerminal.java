package javax.smartcardio;

/**
 * Base class for interfacing to card terminals
 */
public abstract class CardTerminal {

    /**
     * Main constructor
     */
    protected CardTerminal() {
    }

    /** @return the name of this terminal */
    public abstract String getName();

    /** @return true of the terminal has a card present */
    public abstract boolean isCardPresent() throws CardException;

    /**
     * Connect to the card in this terminal
     * @param protocol to use or "*"
     * @return an interface to the card
     * @throws CardException on error
     */
    public abstract Card connect(String protocol) throws CardException;

    /**
     * Wait for a card to be present
     * @param timeout after which to return
     * @return true if there is a card now
     * @throws CardException
     */
    public abstract boolean waitForCardPresent(long timeout) throws CardException;

    /**
     * Wait for a card to be absent
     * @param timeout after which to return
     * @return true if the card is gone now
     * @throws CardException
     */
    public abstract boolean waitForCardAbsent(long timeout) throws CardException;

}
