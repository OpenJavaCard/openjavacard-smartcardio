package javax.smartcardio;

import java.util.Arrays;

public class ATR {

    private final byte[] mBytes;

    private int mHistOffset;
    private int mHistLength;

    public ATR(byte[] bytes) {
        mBytes = bytes.clone();
    }

    public byte[] getBytes() {
        return mBytes.clone();
    }

    public byte[] getHistoricalBytes() {
        byte[] b = new byte[mHistLength];
        System.arraycopy(mBytes, mHistOffset, b, 0, mHistLength);
        return b;
    }

    public String toString() {
        return "ATR: " + mBytes.length + " bytes";
    }

    public int hashCode() {
        return Arrays.hashCode(mBytes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ATR == false) {
            return false;
        }
        ATR other = (ATR)obj;
        return Arrays.equals(this.mBytes, other.getBytes());
    }

    private void parse() {
    }

}
