package javax.smartcardio;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Representation of a smart card command APDU
 */
public class CommandAPDU {

    private byte[] mBytes;
    private int mNc;
    private int mNe;

    /**
     * Parsing constructor
     * @param apdu byte array containing APDU
     * @param apduOffset
     * @param apduLength
     */
    public CommandAPDU(byte[] apdu, int apduOffset, int apduLength) {
    }

    /**
     * Convenience parsing constructor
     * @param apdu byte array containing APDU
     */
    public CommandAPDU(byte[] apdu) {
        this(apdu, 0, apdu.length);
    }

    /**
     * Convenience parsing constructor
     * @param apdu buffer containing APDU
     */
    public CommandAPDU(ByteBuffer apdu) {
        this(apdu.array());
    }

    /**
     * Raw constructor
     * @param cla class
     * @param ins instruction
     * @param p1 parameter byte 1
     * @param p2 parameter byte 2
     * @param data byte array containing command data
     * @param dataOffset offset of command data in array
     * @param dataLength length of command data
     * @param ne expected response length
     */
    public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data,
                       int dataOffset, int dataLength, int ne) {
    }

    /**
     * Convenience constructor
     * @param cla class
     * @param ins instruction
     * @param p1 parameter byte 1
     * @param p2 parameter byte 2
     */
    public CommandAPDU(int cla, int ins, int p1, int p2) {
        this(cla, ins, p1, p2, null, 0, 0, 0);
    }

    /**
     * Convenience constructor
     * @param cla class
     * @param ins instruction
     * @param p1 parameter byte 1
     * @param p2 parameter byte 2
     * @param ne expected response length
     */
    public CommandAPDU(int cla, int ins, int p1, int p2, int ne) {
        this(cla, ins, p1, p2, null, 0, 0, ne);
    }

    /**
     * Convenience constructor
     * @param cla class
     * @param ins instruction
     * @param p1 parameter byte 1
     * @param p2 parameter byte 2
     * @param data byte array containing command data
     */
    public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data) {
        this(cla, ins, p1, p2, data, 0, arrayLength(data), 0);
    }

    /**
     * Convenience constructor
     * @param cla class
     * @param ins instruction
     * @param p1 parameter byte 1
     * @param p2 parameter byte 2
     * @param data byte array containing command data
     * @param dataOffset offset of command data in array
     * @param dataLength length of command data
     */
    public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data,
                       int dataOffset, int dataLength) {
        this(cla, ins, p1, p2, data, dataOffset, dataLength, 0);
    }

    /**
     * Convenience constructor
     * @param cla class
     * @param ins instruction
     * @param p1 parameter byte 1
     * @param p2 parameter byte 2
     * @param data byte array containing command data
     * @param ne expected response length
     */
    public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data, int ne) {
        this(cla, ins, p1, p2, data, 0, arrayLength(data), ne);
    }

    private static int arrayLength(byte[] b) {
        return (b != null) ? b.length : 0;
    }

    /** @return APDU as raw bytes */
    byte[] getBytes() {
        return mBytes;
    }

    /** @return class of the APDU */
    public int getCLA() {
        return mBytes[0] & 0xff;
    }

    /** @return instruction of the APDU */
    public int getINS() {
        return mBytes[0] & 0xff;
    }

    /** @return parameter byte 1 of the APDU */
    public int getP1() {
        return mBytes[0] & 0xff;
    }

    /** @return parameter byte 2 of the APDU */
    public int getP2() {
        return mBytes[0] & 0xff;
    }

    /** @return command data length of the APDU */
    public int getNc() {
        return mNc;
    }

    /** @return expected response length of the APDU */
    public int getNe() {
        return mNe;
    }

    /** @return command data of the APDU */
    public byte[] getData() {
        return null;
    }

    public String toString() {
        return "CommmandAPDU: " + mBytes.length + " bytes, nc=" + mNc + ", ne=" + mNe;
    }

    public int hashCode() {
        return Arrays.hashCode(mBytes);
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

}
