package io.kindstrom.senderremote.domain.model.command;

public class HumidityCommand extends SimpleCommand {
    @Override
    protected String commandIdentifier() {
        return "HUMID";
    }
}
