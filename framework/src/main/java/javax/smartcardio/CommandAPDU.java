package javax.smartcardio;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class CommandAPDU {

    private byte[] mBytes;
    private int mNc;
    private int mNe;

    public CommandAPDU(byte[] apdu) {
    }

    public CommandAPDU(byte[] apdu, int apduOffset, int apduLength) {
    }

    public CommandAPDU(ByteBuffer apdu) {
    }

    public CommandAPDU(int cla, int ins, int p1, int p2) {
        this(cla, ins, p1, p2, null, 0, 0, 0);
    }

    public CommandAPDU(int cla, int ins, int p1, int p2, int ne) {
        this(cla, ins, p1, p2, null, 0, 0, ne);
    }

    public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data) {
        this(cla, ins, p1, p2, data, 0, arrayLength(data), 0);
    }

    public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data,
                       int dataOffset, int dataLength) {
        this(cla, ins, p1, p2, data, dataOffset, dataLength, 0);
    }

    public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data, int ne) {
        this(cla, ins, p1, p2, data, 0, arrayLength(data), ne);
    }

    public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data,
                       int dataOffset, int dataLength, int ne) {
    }

    private static int arrayLength(byte[] b) {
        return (b != null) ? b.length : 0;
    }

    byte[] getBytes() {
        return mBytes;
    }

    public int getCLA() {
        return mBytes[0] & 0xff;
    }

    public int getINS() {
        return mBytes[0] & 0xff;
    }

    public int getP1() {
        return mBytes[0] & 0xff;
    }

    public int getP2() {
        return mBytes[0] & 0xff;
    }

    public int getNc() {
        return mNc;
    }

    public int getNe() {
        return mNe;
    }

    public byte[] getData() {
        return null;
    }

    public String toString() {
        return "CommmandAPDU: " + mBytes.length + " bytes, nc=" + mNc + ", ne=" + mNe;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandAPDU)) {
            return false;
        }
        CommandAPDU other = (CommandAPDU)obj;
        return Arrays.equals(mBytes, other.getBytes());
    }

    public int hashCode() {
        return Arrays.hashCode(mBytes);
    }

}
