package io.kindstrom.senderremote.domain.messaging;

import android.support.annotation.NonNull;

import io.kindstrom.senderremote.domain.model.CommandSendingState;
import io.reactivex.Observable;

public interface CommandSender {
    @NonNull
    Observable<CommandSendingState> send(@NonNull String message);
}
