package org.openjavacard.smartcardio.generic;

import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GenericCard extends Card {

    private final Lock mLock;
    private final Condition mCondition;

    private final GenericCardTerminal mTerminal;

    private Thread mOwner;

    private String mProtocol;
    private ATR mATR;

    protected GenericCard(GenericCardTerminal terminal) {
        mLock = new ReentrantLock();
        mCondition = mLock.newCondition();
        mTerminal = terminal;
    }

    void connected(String protocol, ATR atr) {
        mProtocol = protocol;
        mATR = atr;
    }

    @Override
    public String getProtocol() {
        return mProtocol;
    }

    @Override
    public ATR getATR() {
        return mATR;
    }

    @Override
    public void beginExclusive() throws CardException {
        mLock.lock();
        try {
            if(mOwner != null) {
                throw new CardException("Card already owned by thread " + mOwner);
            }
            mOwner = Thread.currentThread();
        } finally {
            mLock.unlock();
        }
    }

    @Override
    public void endExclusive() throws CardException {
        mLock.lock();
        try {
            if(mOwner == null) {
                throw new CardException("Card not owned");
            }
            if(mOwner != Thread.currentThread()) {
                throw new CardException("Card is owned by other thread " + mOwner);
            }
            mOwner = null;
        } finally {
            mLock.unlock();
        }
    }

}
