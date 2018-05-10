package org.openjavacard.smartcardio.generic;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GenericCardTerminals extends CardTerminals {

    private final Lock mLock;
    private final Condition mCondition;

    private final HashMap<String, GenericCardTerminal> mTerminals;

    private Listener mListener;

    public interface Listener {
        void onTerminalAdded(GenericCardTerminals terminals, GenericCardTerminal terminal);
        void onTerminalRemoved(GenericCardTerminals terminals, GenericCardTerminal terminal);
    }

    protected GenericCardTerminals() {
        super();
        mLock = new ReentrantLock();
        mCondition = mLock.newCondition();
        mTerminals = new HashMap<>();
    }

    public List<GenericCardTerminal> getTerminals() {
        return new ArrayList<>(mTerminals.values());
    }

    public Listener getListener() {
        return mListener;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void addTerminal(GenericCardTerminal terminal) {
        mLock.lock();
        try {
            // add the terminal
            String name = terminal.getName();
            mTerminals.put(name, terminal);
            // signal waiters
            mCondition.signalAll();
        } finally {
            mLock.unlock();
        }
        if(mListener != null) {
            mListener.onTerminalAdded(this, terminal);
        }
    }

    public void removeTerminal(GenericCardTerminal terminal) {
        mLock.lock();
        try {
            // remove the terminal
            String name = terminal.getName();
            mTerminals.remove(name);
            // signal waiters
            mCondition.signalAll();
        } finally {
            mLock.unlock();
        }
        if(mListener != null) {
            mListener.onTerminalRemoved(this, terminal);
        }
    }

    @Override
    public CardTerminal getTerminal(String name) {
        return mTerminals.get(name);
    }

    @Override
    public List<CardTerminal> list() throws CardException {
        ArrayList<CardTerminal> result;
        mLock.lock();
        try {
            result = new ArrayList<CardTerminal>(mTerminals.values());
        } finally {
            mLock.unlock();
        }
        return result;
    }

    @Override
    public List<CardTerminal> list(State state) throws CardException {
        ArrayList<CardTerminal> result = new ArrayList<>();
        mLock.lock();
        try {
            for(GenericCardTerminal terminal: mTerminals.values()) {
                switch(state) {
                    case CARD_PRESENT:
                        if(terminal.isCardPresent()) {
                            result.add(terminal);
                        }
                        break;
                    case CARD_ABSENT:
                        if(!terminal.isCardPresent()) {
                            result.add(terminal);
                        }
                        break;
                }
            }
        } finally {
            mLock.unlock();
        }
        return result;
    }

    @Override
    public boolean waitForChange(long timeout) throws CardException {
        mLock.lock();
        try {
            return mCondition.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new CardException("Wait interrupted", e);
        } finally {
            mLock.unlock();
        }
    }

}
