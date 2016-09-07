package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Pin;


public class PinCommand extends Command {
    private final Pin newPin;

    public PinCommand(int id, String name, String description, Pin newPin) {
        super(id, name, description);
        this.newPin = newPin;
    }

    @Override
    protected String commandIdentifier() {
        return "PIN";
    }

    @Override
    protected String commandStringBody() {
        if (newPin == null) {
            return null;
        }
        return newPin.getPin();
    }
}
