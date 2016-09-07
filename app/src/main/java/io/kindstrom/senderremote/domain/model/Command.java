package io.kindstrom.senderremote.domain.model;

import java.util.Collection;
import java.util.List;

public final class Command {
    private Command() {}

    private static String concat(String identifier, Pin pin) {
        if(pin == null) {
            return null;
        }
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
        if(newPin == null || oldPin == null) {
            return null;
        }
        return concat(concat("PIN", newPin), oldPin);
    }

    public static String limits(Limits limits, Pin pin) {
        if(limits == null || pin == null) {
            return null;
        }

        return "LIMITS " + limits.inCommandFormat() + " " + pin;
    }

    public static String sw(Pin pin) {
        return concat("SW", pin);
    }

    public static String on(int portNumber, Pin pin) {
        return on(portNumber, null, pin);
    }

    public static String on(int portNumber, Duration duration, Pin pin) {
        return outputCommandString("ON", portNumber, duration, pin);
    }

    public static String off(int portNumber, Pin pin) {
        return off(portNumber, null, pin);
    }

    public static String off(int portNumber, Duration duration, Pin pin) {
        return outputCommandString("OFF", portNumber, duration, pin);
    }

    private static String outputCommandString(String identifier, int portNumber, Duration duration,
                                              Pin pin) {
        if(pin == null) {
            return null;
        }

        StringBuilder commandString = new StringBuilder(identifier);
        commandString.append(" ");
        commandString.append(portNumber);
        commandString.append(" ");

        if(duration != null) {
            commandString.append(duration.inCommandFormat());
            commandString.append(" ");
        }

        commandString.append(pin.getPin());
        return commandString.toString();
    }
}
