package io.kindstrom.senderremote.domain.model.command;

public class HumidityCommand extends SimpleCommand {
    public HumidityCommand(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    protected String commandIdentifier() {
        return "HUMID";
    }
}
