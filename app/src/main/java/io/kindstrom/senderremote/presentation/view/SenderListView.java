package io.kindstrom.senderremote.presentation.view;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Sender;

public interface SenderListView {
    void setTitle(String title);

    void setSenders(List<Sender> senders);

    void showSender(Sender sender);
}
