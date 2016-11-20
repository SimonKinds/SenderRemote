package io.kindstrom.senderremote.domain.model.response;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortResponse extends ResponseWithFields<PortResponse.ResponseField> {
    PortResponse(@NonNull String response) {
        super(response);
    }

    private static boolean hasAlert(String s) {
        return s != null && s.equals("!");
    }

    @Override
    List<ResponseField> getFields() {
        if (getError() != Error.NO_ERROR) {
            return null;
        }

        List<PortResponse.ResponseField> ports = new ArrayList<>();

        /*How a response might look:
        Status: IN01=76, IN02=1, IN03=+16%, IN04=!+26C,IN05=-15C, IN06=+13C, IN07=1293, IN08=0,
        OUT01=1, OUT02=0
         */
        //non greedy match at the start to get rid of useless info like commas and so on
        String regexString = ".*?((IN|OUT)\\d+=!?.?\\d+(C|%)?)";
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(getResponse());
        while (matcher.find()) {
            ResponseField port = parsePort(matcher.group(1));
            ports.add(port);
        }
        return ports;
    }

    //will return null if invalid data is entered
    //input string should look something like IN03=-3C
    private ResponseField parsePort(String dataToParse) {
        //1 = IN|OUT, 2 = index, 3 = alert, 4 = +|-, 5 = value, 6 = humid or temp
        String regexString = "(IN|OUT)(\\d+)=(!)?(\\+|-)?(\\d+)(C|%)?";
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(dataToParse);
        if (matcher.matches()) {
            return new ResponseField(
                    //port number
                    Integer.valueOf(matcher.group(2)),
                    getValueFromStrings(matcher.group(4), matcher.group(5)),
                    getPortResponseType(matcher.group(1), matcher.group(6)),
                    hasAlert(matcher.group(3)));
        }
        return null;
    }

    private Type getPortResponseType(String inOrOut, String data) {
        if ("out".equals(inOrOut.toLowerCase())) {
            return Type.OUTPUT;
        }
        if (data != null && data.length() > 0) {
            switch (data.toLowerCase()) {
                case "c":
                    return Type.TEMPERATURE;
                case "%":
                    return Type.HUMIDITY;
                default:
                    return Type.UNKNOWN;
            }
        }
        return Type.COUNTER;
    }

    private int getValueFromStrings(String sign, String value) {
        int val = Integer.valueOf(value);
        //if it's null or just not a negative number, return the number we got
        if (sign == null || !sign.equals("-"))
            return val;
        return -1 * val;
    }

    public enum Type {HUMIDITY, TEMPERATURE, COUNTER, OUTPUT, UNKNOWN}

    public static class ResponseField {
        public final int portNumber;
        public final int value;
        public final Type type;
        public final boolean alert;

        public ResponseField(int portNumber, int value, Type type, boolean alert) {
            this.portNumber = portNumber;
            this.value = value;
            this.type = type;
            this.alert = alert;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof ResponseField) {
                ResponseField o = (ResponseField) obj;
                return portNumber == o.portNumber &&
                        value == o.value &&
                        type == o.type &&
                        alert == o.alert;
            }
            return false;
        }
    }
}
