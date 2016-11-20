package io.kindstrom.senderremote.data.messaging;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.messaging.CommandSender;
import io.kindstrom.senderremote.domain.model.CommandSendingState;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

@PerActivity
public class CommandSenderImpl implements CommandSender {

    private static final int DELAY = 100; //ms

    @Inject
    public CommandSenderImpl() {
    }

    @NonNull
    @Override
    public Observable<CommandSendingState> send(@NonNull final String message) {
        return Observable.create(
                (ObservableEmitter<CommandSendingState> emitter) -> {
                    emitter.onNext(CommandSendingState.SENT);
                    Thread.sleep(DELAY);
                    emitter.onNext(CommandSendingState.DELIVERED);
                    Thread.sleep(DELAY);
                    emitter.onComplete();
                });

    }
}
