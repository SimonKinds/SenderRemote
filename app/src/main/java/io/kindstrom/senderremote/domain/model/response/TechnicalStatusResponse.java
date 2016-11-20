package io.kindstrom.senderremote.domain.model.response;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TechnicalStatusResponse extends ResponseWithFields<TechnicalStatusResponse.ResponseField> {
    private static final String[] SW_FIELDS = {"ID", "Typ", "SW", "Signal", "Switch", "Power",
            "Battery", "Tamper", "IP"};

    public TechnicalStatusResponse(@NonNull String response) {
        super(response);
    }

    @Nullable
    @Override
    List<ResponseField> getFields() {
        if (getError() != Error.NO_ERROR) {
            return null;
        }

        List<ResponseField> swResponseFields = new ArrayList<>();
        //regex will look like .*?(field0|field1|field2...): (.+?),?
        StringBuilder regex = new StringBuilder(".*?(");
        int size = SW_FIELDS.length;
        int cur = 0;
        //add all the fields to the regex
        for (String field : SW_FIELDS) {
            regex.append(field);
            if (++cur < size)
                regex.append("|");
        }
        //make sure to capture the data we are looking for
        regex.append("): ([^,]*),?");
        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(getResponse());
        while (matcher.find()) {
            //group(1) => title, group(2) => data
            swResponseFields.add(new ResponseField(typeFromString(matcher.group(1)),
                    matcher.group(2)));
        }
        return swResponseFields;
    }

    private Type typeFromString(String value) {
        Type type;
        switch (value.toLowerCase()) {
            case "id":
                type = Type.ID;
                break;
            case "typ":
                type = Type.TYPE;
                break;
            case "sw":
                type = Type.SOFTWARE;
                break;
            case "signal":
                type = Type.SIGNAL_STRENGTH;
                break;
            case "switch":
                type = Type.SWITCH;
                break;
            case "power":
                type = Type.POWER;
                break;
            case "battery":
                type = Type.BATTERY;
                break;
            case "tamper":
                type = Type.TAMPER;
                break;
            case "ip":
                type = Type.IP;
                break;
            default:
                type = Type.UNKNOWN;
        }
        return type;
    }

    public enum Type {
        ID, TYPE, SOFTWARE, SIGNAL_STRENGTH, SWITCH, POWER, BATTERY, TAMPER, IP, UNKNOWN
    }

    public class ResponseField {
        public final Type type;
        public final String value;

        public ResponseField(Type type, String value) {
            this.type = type;
            this.value = value;
        }
    }
}
