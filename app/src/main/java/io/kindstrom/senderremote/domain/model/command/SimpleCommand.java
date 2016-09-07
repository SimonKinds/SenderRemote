package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Command;


public abstract class SimpleCommand extends Command {

    protected SimpleCommand(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    protected String commandStringBody() {
        return "";
    }
}
