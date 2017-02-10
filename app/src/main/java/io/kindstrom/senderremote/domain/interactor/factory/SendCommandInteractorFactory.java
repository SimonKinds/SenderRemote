package io.kindstrom.senderremote.domain.interactor.factory;

import android.content.Context;

import javax.inject.Inject;

import io.kindstrom.senderremote.data.messaging.CommandSenderImpl;
import io.kindstrom.senderremote.domain.executor.PostExecutionThread;
import io.kindstrom.senderremote.domain.executor.ThreadExecutor;
import io.kindstrom.senderremote.domain.interactor.SendCommandInteractor;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerGroup;

@PerGroup
public class SendCommandInteractorFactory {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    // TODO: find out a nice way not to have android dependency in the factories
    private final Context context;

    @Inject
    public SendCommandInteractorFactory(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.context = context;
    }

    public SendCommandInteractor create(Sender sender) {
        return new SendCommandInteractor(threadExecutor, postExecutionThread, new CommandSenderImpl(context, sender));
    }
}
