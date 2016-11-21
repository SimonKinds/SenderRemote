package io.kindstrom.senderremote.domain.interactor;

import io.kindstrom.senderremote.domain.executor.PostExecutionThread;
import io.kindstrom.senderremote.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class ObservableInteractor<T> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private Disposable disposable;

    public ObservableInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }


    public final void execute(Consumer<T> consumer) {
        disposable = buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(consumer);
    }

    public final void execute(Consumer<T> consumer, Object... args) {
        disposable = buildUseCaseObservable(args)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(consumer);
    }

    protected Observable<T> buildUseCaseObservable() {
        throw new RuntimeException("Interactor does not implement no-arg use case");
    }

    protected Observable<T> buildUseCaseObservable(Object... args) {
        throw new RuntimeException("Interactor does not implement arg use case");
    }

    public void unsubscribe() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
