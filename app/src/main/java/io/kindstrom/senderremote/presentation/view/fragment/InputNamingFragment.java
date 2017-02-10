package io.kindstrom.senderremote.presentation.view.fragment;

import javax.inject.Inject;

import io.kindstrom.senderremote.presentation.internal.di.components.DaggerGroupComponent;
import io.kindstrom.senderremote.presentation.presenter.InputNamingPresenter;

public class InputNamingFragment extends PortNamingFragment {
    @Inject
    InputNamingPresenter presenter;

    public static InputNamingFragment newInstance(int amountOfPorts) {
        InputNamingFragment frag = new InputNamingFragment();
        frag.setArguments(createArgs(amountOfPorts));
        return frag;
    }

    @Override
    protected void inject() {
        DaggerGroupComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    protected void setupUi() {
        presenter.attach(this);
        presenter.setNumberOfPorts(getArguments().getInt(ARGUMENT_KEY_AMOUNT_OF_PORTS));
    }
}
