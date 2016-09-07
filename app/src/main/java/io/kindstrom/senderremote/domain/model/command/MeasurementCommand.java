package io.kindstrom.senderremote.domain.model.command;

public class MeasurementCommand extends SimpleCommand {

    @Override
    protected String commandIdentifier() {
        return "MEAS";
    }
}
