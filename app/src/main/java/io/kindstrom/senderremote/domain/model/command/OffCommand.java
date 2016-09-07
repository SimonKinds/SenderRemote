package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Duration;

public class OffCommand extends OutputActionCommand {
    public OffCommand(int portNumber) {
        this(portNumber, null);
    }

    public OffCommand(int portNumber, Duration duration) {
        super(portNumber, duration);
    }

    @Override
    protected String commandIdentifier() {
        return "OFF";
    }
}
