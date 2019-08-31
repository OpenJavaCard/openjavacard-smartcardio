package javax.smartcardio;

import java.util.Arrays;

public class ResponseAPDU {
    private byte[] mBytes;

    public ResponseAPDU(byte[] apdu) {
        this.mBytes = apdu.clone();
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    public String toString() {
        return "ResponseAPDU: " + mBytes.length + " bytes";
    }

    public int hashCode() {
        return Arrays.hashCode(mBytes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResponseAPDU)) {
            return false;
        }
        ResponseAPDU other = (ResponseAPDU) obj;
        return Arrays.equals(mBytes, other.getBytes());
    }
}
