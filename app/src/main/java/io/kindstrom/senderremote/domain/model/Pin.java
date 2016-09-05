package io.kindstrom.senderremote.domain.model;

public class Pin {
    private static final int MIN_LENGTH = 4, MAX_LENGTH = 8;

    private final String _pin;

    public static Pin create(String pin) {
        String regex = "[0-9]{" + MIN_LENGTH + "," + MAX_LENGTH + "}";
        if(pin.matches(regex)) {
            return new Pin(pin);
        }

        return null;
    }

    private Pin(String pin) {
        this._pin = pin;
    }

    public String getPin() {
        return _pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pin pin = (Pin) o;

        return _pin.equals(pin._pin);

    }

    @Override
    public int hashCode() {
        return _pin.hashCode();
    }

    @Override
    public String toString() {
        return _pin;
    }
}