package io.kindstrom.senderremote.presentation.view.fragment;

import javax.inject.Inject;

import io.kindstrom.senderremote.presentation.internal.di.components.DaggerSenderComponent;
import io.kindstrom.senderremote.presentation.presenter.OutputNamingPresenter;

public class OutputNamingFragment extends PortNamingFragment {
    @Inject
    OutputNamingPresenter presenter;

    public static OutputNamingFragment newInstance(int amountOfPorts) {
        OutputNamingFragment frag = new OutputNamingFragment();
        frag.setArguments(createArgs(amountOfPorts));
        return frag;
    }

    @Override
    protected void inject() {
        DaggerSenderComponent.builder()
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
