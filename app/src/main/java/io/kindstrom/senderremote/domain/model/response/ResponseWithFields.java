package io.kindstrom.senderremote.domain.model.response;

import android.support.annotation.NonNull;

import java.util.List;

import io.kindstrom.senderremote.domain.model.Response;

abstract class ResponseWithFields<T> extends Response {
    ResponseWithFields(@NonNull String response) {
        super(response);
    }

    abstract List<T> getFields();
}
