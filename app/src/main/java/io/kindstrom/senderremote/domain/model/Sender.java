package io.kindstrom.senderremote.domain.model;

import java.util.List;

public class Sender {
    private final int id;
    private final String name;
    private final String number;
    private final Pin pin;
    private final List<Port> inputs;
    private final List<Port> outputs;
    private final List<Command> commands;

    public Sender(int id, String name, String number, Pin pin, List<Port> inputs, List<Port> outputs, List<Command> commands) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.pin = pin;
        this.inputs = inputs;
        this.outputs = outputs;
        this.commands = commands;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Pin getPin() {
        return pin;
    }

    public List<Port> getInputs() {
        return inputs;
    }

    public List<Port> getOutputs() {
        return outputs;
    }

    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sender sender = (Sender) o;

        return id == sender.id && name.equals(sender.name) && number.equals(sender.number)
                && pin.equals(sender.pin) && (inputs != null ? inputs.equals(sender.inputs) :
                sender.inputs == null && (outputs != null ? outputs.equals(sender.outputs) :
                        sender.outputs == null && (commands != null ?
                                commands.equals(sender.commands) : sender.commands == null)));

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + pin.hashCode();
        result = 31 * result + (inputs != null ? inputs.hashCode() : 0);
        result = 31 * result + (outputs != null ? outputs.hashCode() : 0);
        result = 31 * result + (commands != null ? commands.hashCode() : 0);
        return result;
    }
}
