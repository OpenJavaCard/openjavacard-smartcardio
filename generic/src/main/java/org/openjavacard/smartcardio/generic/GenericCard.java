package org.openjavacard.smartcardio.generic;

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

    protected GenericCard(GenericCardTerminal terminal) {
        mLock = new ReentrantLock();
        mCondition = mLock.newCondition();
        mTerminal = terminal;
    }

    @Override
    public void beginExclusive() throws CardException {
    }

    @Override
    public void endExclusive() throws CardException {
    }

}
