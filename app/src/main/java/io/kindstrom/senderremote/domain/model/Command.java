package io.kindstrom.senderremote.domain.model;

public abstract class Command {
    private final int id;
    private final String name;
    private final String description;

    protected Command(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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

    protected abstract String commandIdentifier();

    protected abstract String commandStringBody();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (id != command.id) return false;
        if (!name.equals(command.name)) return false;
        return description.equals(command.description);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
