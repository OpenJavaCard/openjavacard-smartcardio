package javax.smartcardio;

import java.util.List;

public abstract class CardTerminals {

    protected CardTerminals() {
    }

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

    public List<CardTerminal> list() throws CardException {
        return list(State.ALL);
    }

    public void waitForChange() throws CardException {
        waitForChange(0);
    }

    public abstract List<CardTerminal> list(State state) throws CardException;

    public abstract boolean waitForChange(long timeout) throws CardException;

    public enum State {
        ALL,
        CARD_PRESENT,
        CARD_ABSENT,
        CARD_INSERTION,
        CARD_REMOVAL,
    }

}
