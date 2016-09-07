package io.kindstrom.senderremote.domain.model.command;

public class TechnicalStatusCommand extends SimpleCommand {
    @Override
    protected String commandIdentifier() {
        return "SW";
    }
}
