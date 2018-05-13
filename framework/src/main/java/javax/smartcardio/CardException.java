package javax.smartcardio;

/**
 * Base class for smart card exceptions
 */
public class CardException extends Exception {

    /**
     * Exception constructor
     * @param message for exception
     */
    public CardException(String message) {
        super(message);
    }

    /**
     * Exception constructor
     * @param message for exception
     * @param cause for exception
     */
    public CardException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception constructor
     * @param cause for exception
     */
    public CardException(Throwable cause) {
        super(cause);
    }

}
