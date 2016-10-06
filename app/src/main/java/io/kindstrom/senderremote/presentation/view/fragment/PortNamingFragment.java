package io.kindstrom.senderremote.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.kindstrom.senderremote.R;
import io.kindstrom.senderremote.presentation.internal.di.components.ApplicationComponent;
import io.kindstrom.senderremote.presentation.view.PortNamingView;
import io.kindstrom.senderremote.presentation.view.activity.BaseActivity;


public abstract class PortNamingFragment extends Fragment implements PortNamingView {
    protected static final String ARGUMENT_KEY_AMOUNT_OF_PORTS = "port";
    @BindView(R.id.ll_port_naming_rows)
    LinearLayout portNamingRows;
    private SenderCreationNavigator navigator;

    protected static Bundle createArgs(int amountOfPorts) {
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_KEY_AMOUNT_OF_PORTS, amountOfPorts);
        return args;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof SenderCreationNavigator) {
            navigator = (SenderCreationNavigator) context;
        } else {
            throw new RuntimeException("Container activity must implement interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_port_naming, container, false);

        inject();
        ButterKnife.bind(this, v);
        setupUi();

        return v;
    }

    protected abstract void inject();

    protected abstract void setupUi();

    @Override
    public void setDefaultPortNames(@NonNull List<String> names) {
        portNamingRows.removeAllViews();
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        for (String name : names) {
            TextInputEditText et = new TextInputEditText(getActivity());
            et.setLayoutParams(layoutParams);
            et.setText(name);

            TextInputLayout row = new TextInputLayout(getActivity());
            row.setLayoutParams(layoutParams);
            // in the case of creation, the default name of the port is the best one for hinting
            row.setHint(name);

            row.addView(et);
            portNamingRows.addView(row);
        }
    }

    @Override
    public void navigateSoSenderCreationView() {
        getActivity().finish();
    }

    @Override
    public String[] getPortNames() {
        int portAmount = portNamingRows.getChildCount();
        String[] portNames = new String[portAmount];

        for (int i = 0; i < portAmount; ++i) {
            TextInputLayout textContainer = (TextInputLayout) portNamingRows.getChildAt(i);
            EditText et = textContainer.getEditText();
            if (et != null) {
                String name = et.getText().toString();
                portNames[i] = name;
            } else {
                throw new RuntimeException("Edit text was null");
            }
        }

        return portNames;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((BaseActivity) getActivity()).getApplicationComponent();
    }

    public interface SenderCreationNavigator {
        void navigateToSenderCreationView();
    }
}
