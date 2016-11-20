package io.kindstrom.senderremote.domain.interactor;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.executor.PostExecutionThread;
import io.kindstrom.senderremote.domain.executor.ThreadExecutor;
import io.kindstrom.senderremote.domain.messaging.ResponseReceiver;
import io.kindstrom.senderremote.domain.model.Response;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.reactivex.Observable;

@PerActivity
public class ReceiveResponseInteractor extends ObservableInteractor<Response> {
    private final ResponseReceiver responseReceiver;

    @Inject
    public ReceiveResponseInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ResponseReceiver responseReceiver) {
        super(threadExecutor, postExecutionThread);

        this.responseReceiver = responseReceiver;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable() {
        return responseReceiver.listen();
    }
}
