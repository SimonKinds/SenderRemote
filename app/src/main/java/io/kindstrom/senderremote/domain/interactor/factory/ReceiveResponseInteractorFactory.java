package io.kindstrom.senderremote.domain.interactor.factory;

import android.content.Context;

import javax.inject.Inject;

import io.kindstrom.senderremote.data.messaging.ResponseReceiverImpl;
import io.kindstrom.senderremote.domain.executor.PostExecutionThread;
import io.kindstrom.senderremote.domain.executor.ThreadExecutor;
import io.kindstrom.senderremote.domain.interactor.ReceiveResponseInteractor;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerGroup;

@PerGroup
public class ReceiveResponseInteractorFactory {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    // TODO: find out a nice way not to have android dependency in the factories
    private final Context context;

    @Inject
    public ReceiveResponseInteractorFactory(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.context = context;
    }

    public ReceiveResponseInteractor create(Sender sender) {
        return new ReceiveResponseInteractor(threadExecutor, postExecutionThread, new ResponseReceiverImpl(context, sender));
    }
}
