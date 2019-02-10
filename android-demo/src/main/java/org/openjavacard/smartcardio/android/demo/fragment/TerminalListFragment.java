package org.openjavacard.smartcardio.android.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.openjavacard.smartcardio.android.demo.R;
import org.openjavacard.smartcardio.android.demo.activity.MainActivity;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;
import org.openjavacard.smartcardio.generic.GenericCardTerminals;

public class TerminalListFragment extends ListFragment {

    private MainActivity mActivity;

    private GenericCardTerminals mTerminals;
    private ArrayAdapter<GenericCardTerminal> mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity)getActivity();
        update();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new ArrayAdapter<>(getContext(), R.layout.item_terminal);
        setListAdapter(mAdapter);
        update();
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    public void setTerminals(GenericCardTerminals terminals) {
        mTerminals = terminals;
        update();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        GenericCardTerminal terminal = mAdapter.getItem(position);
        mActivity.switchToTerminalInfo(terminal);
    }

    private void update() {
        if(mActivity != null) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.clear();
                    if(mTerminals != null) {
                        mAdapter.addAll(mTerminals.getTerminals());
                    }
                }
            });
        }
    }

}
