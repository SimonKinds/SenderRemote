package io.kindstrom.senderremote.presentation.view;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Group;

public interface GroupListView {
    void setGroups(List<Group> groups);

    void showGroup(Group group);

    void showCreateGroupView();
}
