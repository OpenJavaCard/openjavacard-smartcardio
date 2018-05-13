package javax.smartcardio;

import java.util.List;

/**
 * Base class for interfacing to a set of terminals
 */
public abstract class CardTerminals {

    /** Reader states */
    public enum State {
        ALL,
        CARD_PRESENT,
        CARD_ABSENT,
        CARD_INSERTION,
        CARD_REMOVAL,
    }

    /**
     * Main constructor
     */
    protected CardTerminals() {
    }

    /**
     * Get a specific terminal by name
     * @param name of the terminal
     * @return the terminal or null
     */
    public CardTerminal getTerminal(String name) {
        try {
            for (CardTerminal terminal : list()) {
                if (terminal.getName().equals(name)) {
                    return terminal;
                }
            }
            return null;
        } catch (CardException e) {
            return null;
        }
    }

    /**
     * List all terminals in this set
     * @return list of all terminals
     * @throws CardException on error
     */
    public List<CardTerminal> list() throws CardException {
        return list(State.ALL);
    }

    /**
     * List all terminals in the given state
     * @param state to query
     * @return list of terminals in given state
     * @throws CardException on error
     */
    public abstract List<CardTerminal> list(State state) throws CardException;

    /**
     * Wait for any addition, removal or state change
     * <p/>
     * Convenience variant with infinite timeout.
     * <p/>
     * @throws CardException on error
     */
    public void waitForChange() throws CardException {
        waitForChange(0);
    }

    /**
     * Wait for any addition, removal or state change
     * @throws CardException on error
     */
    public abstract boolean waitForChange(long timeout) throws CardException;

}
