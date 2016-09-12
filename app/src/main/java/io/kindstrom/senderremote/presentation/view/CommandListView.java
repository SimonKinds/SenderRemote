package io.kindstrom.senderremote.presentation.view;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Command;

public interface CommandListView {
    void setTitle(String title);

    void setCommands(List<Command> commands);

    void showCommand(Command command);
}
