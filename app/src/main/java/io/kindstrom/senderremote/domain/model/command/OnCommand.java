package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Duration;

public class OnCommand extends OutputActionCommand {

    public OnCommand(int id, String name, String description) {
        super(id, name, description);
    }

    public OnCommand(int id, String name, String description, int portNumber) {
        super(id, name, description, portNumber, null);
    }

    public OnCommand(int id, String name, String description, int portNumber, Duration duration) {
        super(id, name, description, portNumber, duration);
    }

    @Override
    protected String commandIdentifier() {
        return "ON";
    }
}
