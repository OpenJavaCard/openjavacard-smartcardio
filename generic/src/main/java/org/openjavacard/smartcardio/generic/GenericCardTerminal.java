package org.openjavacard.smartcardio.generic;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GenericCardTerminal extends CardTerminal {

    private final Lock mLock;
    private final Condition mCondition;

    private final GenericCardTerminals mTerminals;
    private final String mName;

    protected GenericCardTerminal(GenericCardTerminals terminals, String name) {
        mLock = new ReentrantLock();
        mCondition = mLock.newCondition();
        mTerminals = terminals;
        mName = name;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public boolean waitForCardPresent(long timeout) throws CardException {
        return false;
    }

    @Override
    public boolean waitForCardAbsent(long timeout) throws CardException {
        return false;
    }

}
