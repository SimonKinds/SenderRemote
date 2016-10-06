package io.kindstrom.senderremote.presentation.view;

import android.support.annotation.NonNull;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Group;

public interface SenderCreateView {
    void returnToPreviousView();

    void showGroups(@NonNull List<Group> groups);

    void selectGroup(@NonNull Group group);

    void showPortNamingView(int amountOfInputs, int amountOfOutputs);

    @NonNull
    String getName();

    @NonNull
    String getNumber();

    @NonNull
    String getPin();

    @NonNull
    List<Integer> getGroups();

    void showErrorName();

    void showErrorNumber();

    void showErrorPinTooShort();

    void showErrorPinNeeded();
}
