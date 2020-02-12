package org.openjavacard.smartcardio.android.omapi;

import android.annotation.TargetApi;
import android.se.omapi.Channel;
import org.openjavacard.smartcardio.generic.GenericCardChannel;

import javax.smartcardio.CardException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class OmapiCardChannel extends GenericCardChannel {

    /** True if this is the basic channel */
    private boolean mIsBasic;
    /** OMAPI channel object */
    private Channel mChannel;

    OmapiCardChannel(OmapiCard card, boolean isBasic, Channel channel) {
        // always pass 0 for the channel number
        // so the generic code does not change CLA
        super(card, 0);
        mIsBasic = isBasic;
        mChannel = channel;
    }

    /**
     * Return the channel number
     * <p/>
     * On OMAPI we always return 0 or 1 because
     * we do not know the actual channel number.
     * <p/>
     * @return fake channel number
     */
    @Override
    public int getChannelNumber() {
        if(mIsBasic) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    @TargetApi(28)
    public int transmit(ByteBuffer command, ByteBuffer response) throws CardException {
        byte[] commandBytes = new byte[command.position()];
        command.get(commandBytes);
        byte[] responseBytes;
        try {
            responseBytes = mChannel.transmit(commandBytes);
        } catch (IOException e) {
            throw new CardException("Could not transmit command", e);
        }
        response.put(responseBytes);
        return responseBytes.length;
    }

    @Override
    @TargetApi(28)
    public void close() throws CardException {
        if(mChannel.isOpen()) {
            mChannel.close();
        }
    }

}
