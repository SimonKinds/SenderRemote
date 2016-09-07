package io.kindstrom.senderremote.domain.model;

public class Sender {
    private final int id;
    private final String number;
    private final Pin pin;

    public Sender(int id, String number, Pin pin) {
        this.id = id;
        this.number = number;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Pin getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sender sender = (Sender) o;

        return id == sender.id && (number != null ? number.equals(sender.number) :
                sender.number == null && (pin != null ? pin.equals(sender.pin) : sender.pin == null));

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", pin=" + pin.getPin() +
                '}';
    }
}
