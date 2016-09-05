package io.kindstrom.senderremote.domain.model;

public abstract class Command {
    public String getCommandString(Pin pin) {
        if(pin == null) {
            return null;
        }
        String commandBody = getCommandBody();
        if(commandBody == null) {
            return null;
        }
        if(!commandBody.isEmpty()) {
            commandBody += " ";
        }
        return getCommandIdentifier() + " " + commandBody + pin.getPin();
    }

    protected String getCommandBody() {
        return "";
    }

    protected abstract String getCommandIdentifier();
}
