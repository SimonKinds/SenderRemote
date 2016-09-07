package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Command;


public abstract class SimpleCommand extends Command {
    @Override
    protected String commandStringBody() {
        return "";
    }
}
