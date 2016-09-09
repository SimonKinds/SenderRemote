package io.kindstrom.senderremote.presentation.presenter;

public interface Presenter<E> {
    void attach(E view);

    void detach();
}
