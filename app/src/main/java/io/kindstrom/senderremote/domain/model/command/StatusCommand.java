package io.kindstrom.senderremote.domain.model.command;

public class StatusCommand extends SimpleCommand {
    @Override
    protected String commandIdentifier() {
        return "STATUS";
    }
}
