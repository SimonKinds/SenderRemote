package io.kindstrom.senderremote.domain.model;

import android.support.annotation.Nullable;

public abstract class Command {
    private final int id;
    private final String name;
    private final String description;

    protected Command(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Nullable
    public final String commandString(Pin pin) {
        String body = commandStringBody();
        if (pin == null || body == null) {
            return null;
        }

        StringBuilder commandString = new StringBuilder(commandIdentifier());
        commandString.append(" ");
        if (!body.isEmpty()) {
            commandString.append(body);
            commandString.append(" ");
        }
        commandString.append(pin);
        return commandString.toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    protected abstract String commandIdentifier();

    protected abstract String commandStringBody();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        return id == command.id && name.equals(command.name) && description.equals(command.description);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
