package io.kindstrom.senderremote.presentation.view;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.domain.model.Sender;

public interface GroupListView {
    void setGroups(List<Group> groups);

    void viewSender(Sender sender);
}
