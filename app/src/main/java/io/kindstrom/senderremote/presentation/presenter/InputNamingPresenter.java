package io.kindstrom.senderremote.presentation.presenter;

import android.content.res.Resources;

import javax.inject.Inject;

import io.kindstrom.senderremote.R;

public class InputNamingPresenter extends PortNamingPresenter {

    private final Resources resources;

    @Inject
    public InputNamingPresenter(Resources resources) {
        this.resources = resources;
    }

    @Override
    protected String getPortNamePrefix() {
        return resources.getString(R.string.input);
    }
}
