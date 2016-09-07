package io.kindstrom.senderremote.domain.model.command;


public class TemperatureCommand extends SimpleCommand {
    @Override
    protected String commandIdentifier() {
        return "TEMP";
    }
}
