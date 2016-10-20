package io.kindstrom.senderremote.domain.messaging;

import android.support.annotation.NonNull;

import io.kindstrom.senderremote.domain.model.Response;
import io.reactivex.Observable;

public interface ResponseReceiver {
    @NonNull
    Observable<Response> listen();

    void stop();
}
