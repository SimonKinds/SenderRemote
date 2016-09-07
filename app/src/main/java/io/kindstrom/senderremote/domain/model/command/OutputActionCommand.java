package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Duration;


public abstract class OutputActionCommand extends Command {
    private final int portNumber;
    private final Duration duration;

    protected OutputActionCommand(int portNumber, Duration duration) {
        this.portNumber = portNumber;
        this.duration = duration;
    }

    @Override
    protected String commandStringBody() {
        StringBuilder commandBody = new StringBuilder();
        commandBody.append(portNumber);

        if (duration != null) {
            commandBody.append(" ");
            commandBody.append(duration.inCommandFormat());
        }

        return commandBody.toString();
    }
}
