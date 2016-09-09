package io.kindstrom.senderremote.presentation.presenter;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.interactor.GetSendersInteractor;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.view.SenderListView;

@PerActivity
public class SenderListPresenter implements Presenter<SenderListView> {

    private final GetSendersInteractor getSendersInteractor;

    @Inject
    public SenderListPresenter(GetSendersInteractor getSendersInteractor) {
        this.getSendersInteractor = getSendersInteractor;
    }

    @Override
    public void attach(SenderListView view) {
        view.setSenders(getSendersInteractor.execute());
    }

    @Override
    public void detach() {
        // nop
    }
}
