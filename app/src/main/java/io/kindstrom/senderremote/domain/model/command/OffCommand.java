package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Duration;

public class OffCommand extends OutputActionCommand {

    public OffCommand(int id, String name, String description) {
        super(id, name, description);
    }

    public OffCommand(int id, String name, String description, int portNumber) {
        super(id, name, description, portNumber, null);
    }

    public OffCommand(int id, String name, String description, int portNumber, Duration duration) {
        super(id, name, description, portNumber, duration);
    }

    @Override
    protected String commandIdentifier() {
        return "OFF";
    }
}
