package io.kindstrom.senderremote.domain.model;

public abstract class Command {

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
}
