package io.kindstrom.senderremote.domain.model.command;

public class TechnicalStatusCommand extends SimpleCommand {
    public TechnicalStatusCommand(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    protected String commandIdentifier() {
        return "SW";
    }
}
