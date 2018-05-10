package javax.smartcardio;

public class ATR {

    private final byte[] mBytes;

    public ATR(byte[] bytes) {
        mBytes = bytes.clone();
    }

}
