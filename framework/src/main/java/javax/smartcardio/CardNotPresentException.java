package javax.smartcardio;

public class CardNotPresentException extends CardException {

    public CardNotPresentException(String message) {
        super(message);
    }

    public CardNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardNotPresentException(Throwable cause) {
        super(cause);
    }

}
