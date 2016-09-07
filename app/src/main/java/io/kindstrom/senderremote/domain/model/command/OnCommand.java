package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Duration;

public class OnCommand extends OutputActionCommand {

    public OnCommand(int portNumber) {
        this(portNumber, null);
    }

    public OnCommand(int portNumber, Duration duration) {
        super(portNumber, duration);
    }

    @Override
    protected String commandIdentifier() {
        return "ON";
    }
}
