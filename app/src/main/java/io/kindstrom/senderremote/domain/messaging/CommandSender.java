package io.kindstrom.senderremote.domain.messaging;

import io.kindstrom.senderremote.domain.model.CommandSendingState;
import io.reactivex.Observable;

public interface CommandSender {
    Observable<CommandSendingState> send(String message);
}
