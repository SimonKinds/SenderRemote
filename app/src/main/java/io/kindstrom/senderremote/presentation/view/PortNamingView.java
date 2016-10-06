package io.kindstrom.senderremote.presentation.view;

import android.support.annotation.NonNull;

import java.util.List;

public interface PortNamingView {
    void setDefaultPortNames(@NonNull List<String> names);

    void navigateSoSenderCreationView();

    String[] getPortNames();
}
