package io.kindstrom.senderremote.domain.model;

import android.support.annotation.NonNull;

public class Response {
    private final String response;

    public Response(@NonNull String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public Error getError() {
        Error error = Error.NO_ERROR;

        if (response.startsWith("Error")) {
            switch (response.toLowerCase()) {
                case "error, invalid pin code":
                    error = Error.INVALID_PIN;
                    break;
                case "error, unknown command":
                    error = Error.UNKNOWN_COMMAND;
                    break;
                default:
                    error = Error.UNKNOWN_ERROR;
            }
        }

        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response1 = (Response) o;

        return response.equals(response1.response);

    }

    @Override
    public int hashCode() {
        return response.hashCode();
    }

    @Override
    public String toString() {
        return "Response{" +
                "response='" + response + '\'' +
                '}';
    }

    enum Error {
        NO_ERROR,
        INVALID_PIN,
        UNKNOWN_COMMAND,
        UNKNOWN_ERROR
    }
}
