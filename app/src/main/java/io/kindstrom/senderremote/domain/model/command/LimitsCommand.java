package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Limits;


public class LimitsCommand extends Command {

    private final Limits limits;

    public LimitsCommand(int id, String name, String description) {
        this(id, name, description, null);
    }

    public LimitsCommand(int id, String name, String description, Limits limits) {
        super(id, name, description);
        this.limits = limits;
    }

    @Override
    protected String commandIdentifier() {
        return "LIMITS";
    }

    @Override
    protected String commandStringBody() {
        return limits.inCommandFormat();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LimitsCommand that = (LimitsCommand) o;

        return limits.equals(that.limits);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + limits.hashCode();
        return result;
    }
}
