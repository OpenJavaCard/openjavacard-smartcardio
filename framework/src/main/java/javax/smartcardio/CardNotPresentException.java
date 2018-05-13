package javax.smartcardio;

/**
 * Exception for when the card is not present
 */
public class CardNotPresentException extends CardException {

    /**
     * Exception constructor
     * @param message for exception
     */
    public CardNotPresentException(String message) {
        super(message);
    }

    /**
     * Exception constructor
     * @param message for exception
     * @param cause for exception
     */
    public CardNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception constructor
     * @param cause for exception
     */
    public CardNotPresentException(Throwable cause) {
        super(cause);
    }

}
