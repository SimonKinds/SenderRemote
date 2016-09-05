package io.kindstrom.senderremote.domain.model.command;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Pin;


public class PinCommand extends Command {
    private final Pin newPin;

    public PinCommand(Pin newPin) {
        this.newPin = newPin;
    }

    @Override
    protected String getCommandIdentifier() {
        return "PIN";
    }

    @Override
    protected String getCommandBody() {
        if(newPin == null) {
            return null;
        }

        return newPin.getPin();
    }

    @Override
    public String toString() {
        return "PinCommand{" +
                "newPin=" + newPin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PinCommand that = (PinCommand) o;

        return newPin != null ? newPin.equals(that.newPin) : that.newPin == null;

    }

    @Override
    public int hashCode() {
        return newPin != null ? newPin.hashCode() : 0;
    }
}
