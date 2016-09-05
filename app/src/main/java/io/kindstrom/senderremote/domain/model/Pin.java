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
}