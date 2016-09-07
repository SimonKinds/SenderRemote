package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Duration;


public abstract class OutputActionCommand extends Command {
    private final int portNumber;
    private final Duration duration;

    protected OutputActionCommand(int id, String name, String description, int portNumber, Duration duration) {
        super(id, name, description);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OutputActionCommand that = (OutputActionCommand) o;

        return portNumber == that.portNumber && (duration != null ? duration.equals(that.duration) : that.duration == null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + portNumber;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }
}
