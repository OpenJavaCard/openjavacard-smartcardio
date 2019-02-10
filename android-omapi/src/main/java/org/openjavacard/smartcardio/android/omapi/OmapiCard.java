package org.openjavacard.smartcardio.android.omapi;

import android.annotation.TargetApi;
import android.se.omapi.Channel;
import android.se.omapi.Session;
import org.openjavacard.smartcardio.generic.GenericCard;

import javax.smartcardio.CardException;
import java.io.IOException;

public class OmapiCard extends GenericCard {

    private Session mSession;

    OmapiCard(OmapiCardTerminal terminal, Session session) {
        super(terminal);
        mSession = session;
    }

    @Override
    @TargetApi(28)
    public OmapiCardChannel getBasicChannel() {
        try {
            Channel channel = mSession.openBasicChannel(null);
            return new OmapiCardChannel(this, true, channel);
        } catch (IOException e) {
            throw new IllegalStateException("Could not get basic channel", e);
        }
    }

    @Override
    @TargetApi(28)
    public OmapiCardChannel openLogicalChannel() throws CardException {
        try {
            Channel channel = mSession.openLogicalChannel(null);
            return new OmapiCardChannel(this, false, channel);
        } catch (IOException e) {
            throw new CardException("Could not open logical channel", e);
        }
    }

    @Override
    @TargetApi(28)
    public void disconnect(boolean reset) {
        if(reset) {
            throw new UnsupportedOperationException("Card reset not supported by OMAPI");
        }
        if(!mSession.isClosed()) {
            mSession.closeChannels();
            mSession.close();
        }
    }

}
