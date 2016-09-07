package io.kindstrom.senderremote.domain.model.command;

public class StatusCommand extends SimpleCommand {
    public StatusCommand(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    protected String commandIdentifier() {
        return "STATUS";
    }
}
