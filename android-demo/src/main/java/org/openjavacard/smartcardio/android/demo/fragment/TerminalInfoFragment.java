package org.openjavacard.smartcardio.android.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import org.openjavacard.smartcardio.android.demo.R;
import org.openjavacard.smartcardio.android.demo.activity.MainActivity;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;

public class TerminalInfoFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = TerminalInfoFragment.class.getName();

    private MainActivity mActivity;

    private GenericCardTerminal mTerminal;

    private CardView mTerminalLayout;
    private CardView mCardLayout;

    private Button mDismissButton;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.fragment_terminal, container, false);
        mTerminalLayout = view.findViewById(R.id.terminal_layout);
        mCardLayout = view.findViewById(R.id.card_layout);
        mDismissButton = view.findViewById(R.id.dismiss_button);
        mDismissButton.setOnClickListener(this);
        return view;
    }

    public void setTerminal(GenericCardTerminal terminal) {
        mTerminal = terminal;
        updateViews();
    }

    private void updateViews() {
    }

    @Override
    public void onClick(View v) {
        if(v == mDismissButton) {
            mActivity.switchToTerminalList();
        }
    }

}
