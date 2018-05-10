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

import java.util.List;

public class TerminalListFragment extends ListFragment {

    private MainActivity mActivity;

    private ArrayAdapter<GenericCardTerminal> mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity)getActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new ArrayAdapter<>(getContext(), R.layout.item_terminal);
        setListAdapter(mAdapter);
    }

    public void setTerminals(final List<GenericCardTerminal> terminals) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.clear();
                mAdapter.addAll(terminals);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        GenericCardTerminal terminal = mAdapter.getItem(position);
        mActivity.switchToTerminalInfo(terminal);
    }

}
