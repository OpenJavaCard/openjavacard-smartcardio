package javax.smartcardio;

public class CardException extends Exception {

    public CardException(String message) {
        super(message);
    }

    public CardException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardException(Throwable cause) {
        super(cause);
    }

}
