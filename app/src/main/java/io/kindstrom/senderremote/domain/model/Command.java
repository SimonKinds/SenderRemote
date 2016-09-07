package io.kindstrom.senderremote.domain.model;

public final class Command {
    private Command() {}

    private static String concat(String identifier, Pin pin) {
        return identifier + " " + pin.getPin();
    }

    public static String temperature(Pin pin) {
        return concat("TEMP", pin);
    }

    public static String humidity(Pin pin) {
        return concat("HUMID", pin);
    }

    public static String measurements(Pin pin) {
        return concat("MEAS", pin);
    }

    public static String status(Pin pin) {
        return concat("STATUS", pin);
    }

    public static String pin(Pin newPin, Pin oldPin) {
        return concat(concat("PIN", newPin), oldPin);
    }
}
