package io.kindstrom.senderremote.domain.interactor;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.executor.PostExecutionThread;
import io.kindstrom.senderremote.domain.executor.ThreadExecutor;
import io.kindstrom.senderremote.domain.messaging.CommandSender;
import io.kindstrom.senderremote.domain.model.CommandSendingState;
import io.reactivex.Observable;

public class SendCommandInteractor extends ObservableInteractor<CommandSendingState> {
    private final CommandSender commandSender;

    @Inject
    public SendCommandInteractor(ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread,
                                 CommandSender commandSender) {
        super(threadExecutor, postExecutionThread);
        this.commandSender = commandSender;
    }

    @Override
    @Nullable
    protected Observable<CommandSendingState> buildUseCaseObservable(Object[] args) {
        String message = (String) args[0];
        if (message == null) {
            return null;
        }
        return commandSender.send(message);
    }
}
