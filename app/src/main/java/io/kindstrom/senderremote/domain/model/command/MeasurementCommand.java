package io.kindstrom.senderremote.domain.model.command;

public class MeasurementCommand extends SimpleCommand {

    public MeasurementCommand(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    protected String commandIdentifier() {
        return "MEAS";
    }
}
