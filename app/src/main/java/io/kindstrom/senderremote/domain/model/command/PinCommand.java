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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PinCommand that = (PinCommand) o;

        return newPin != null ? newPin.equals(that.newPin) : that.newPin == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (newPin != null ? newPin.hashCode() : 0);
        return result;
    }
}
