package io.kindstrom.senderremote.presentation.view;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Sender;

public interface GroupCreateView {
    void returnToPreviousView();

    void showSenders(List<Sender> senders);

    void hideSenderSelectionView();
}
