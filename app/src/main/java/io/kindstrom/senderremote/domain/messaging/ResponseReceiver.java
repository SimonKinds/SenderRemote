package io.kindstrom.senderremote.domain.messaging;

import io.kindstrom.senderremote.domain.model.Response;
import io.reactivex.Observable;

public interface ResponseReceiver {
    Observable<Response> listen();

    void stop();
}
