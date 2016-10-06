package io.kindstrom.senderremote.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.presentation.view.PortNamingView;

public abstract class PortNamingPresenter implements Presenter<PortNamingView> {
    private PortNamingView view;

    @Override
    public void attach(PortNamingView view) {
        this.view = view;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        view.setDefaultPortNames(createDefaultPortNames(numberOfPorts));
    }

    @Override
    public void detach() {
        view = null;
    }

    private List<String> createDefaultPortNames(int amount) {
        List<String> portNames = new ArrayList<>(amount);

        String prefix = getPortNamePrefix();

        for (int i = 1; i <= amount; ++i) {
            portNames.add(prefix + " " + i);
        }
        return portNames;
    }

    protected abstract String getPortNamePrefix();
}
