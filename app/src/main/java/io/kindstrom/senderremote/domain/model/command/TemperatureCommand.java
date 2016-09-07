package io.kindstrom.senderremote.domain.model.command;


public class TemperatureCommand extends SimpleCommand {
    public TemperatureCommand(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    protected String commandIdentifier() {
        return "TEMP";
    }
}
