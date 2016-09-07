package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Limits;


public class LimitsCommand extends Command {

    private final Limits limits;

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
}
