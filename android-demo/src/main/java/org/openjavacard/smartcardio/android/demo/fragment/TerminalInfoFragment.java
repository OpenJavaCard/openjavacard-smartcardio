package org.openjavacard.smartcardio.android.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import org.openjavacard.smartcardio.android.demo.R;
import org.openjavacard.smartcardio.android.demo.activity.MainActivity;
import org.openjavacard.smartcardio.generic.GenericCardTerminal;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;

public class TerminalInfoFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = TerminalInfoFragment.class.getName();

    /** Our parent activity */
    private MainActivity mActivity;
    /** Fragment manager (for stack interactions) */
    private FragmentManager mFragmentManager;

    /** True once views have been instantiated */
    private boolean mInitialized;

    private GenericCardTerminal mTerminal;
    private Card mCard;

    private CardView mTerminalLayout;
    private CardView mCardLayout;

    private TextView mTerminalClass;
    private TextView mTerminalName;
    private TextView mCardClass;
    private TextView mCardProtocol;
    private TextView mCardATR;

    private Button mDismissButton;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity)getActivity();
        mFragmentManager = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        // instantiate things
        View view = inflater.inflate(R.layout.fragment_terminal, container, false);
        // get layout containers
        mTerminalLayout = view.findViewById(R.id.terminal_layout);
        mCardLayout = view.findViewById(R.id.card_layout);
        // get info fields
        mTerminalClass = view.findViewById(R.id.terminal_info_class);
        mTerminalName = view.findViewById(R.id.terminal_info_name);
        mCardClass = view.findViewById(R.id.card_info_class);
        mCardProtocol = view.findViewById(R.id.card_info_protocol);
        mCardATR = view.findViewById(R.id.card_info_atr);
        // get dismiss button
        mDismissButton = view.findViewById(R.id.dismiss_button);
        mDismissButton.setOnClickListener(this);
        // we are now initialized
        mInitialized = true;
        // update views
        updateViews();
        // return root
        return view;
    }

    public void setTerminal(GenericCardTerminal terminal) {
        Card card = null;
        try {
            if(terminal != null) {
                if(terminal.isCardPresent()) {
                    card = terminal.connect("*");
                }
            }
        } catch (CardException e) {
            e.printStackTrace();
        }
        // remember things
        mTerminal = terminal;
        mCard = card;
        // update views
        updateViews();
    }

    private void updateViews() {
        if(!mInitialized) {
            return;
        }

        mTerminalLayout.setVisibility(mTerminal == null ? View.GONE : View.VISIBLE);
        if(mTerminal != null) {
            mTerminalClass.setText(mTerminal.getClass().getSimpleName());
            mTerminalName.setText(mTerminal.getName());
        }

        mCardLayout.setVisibility(mCard == null ? View.GONE : View.VISIBLE);
        if(mCard != null) {
            mCardClass.setText(mCard.getClass().getSimpleName());
            mCardProtocol.setText(mCard.getProtocol());
        }
    }

    @Override
    public void onClick(View v) {
        if(v == mDismissButton) {
            mFragmentManager.popBackStack();
        }
    }

}
