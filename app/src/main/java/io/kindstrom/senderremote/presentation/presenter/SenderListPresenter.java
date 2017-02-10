package io.kindstrom.senderremote.presentation.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Named;

import io.kindstrom.senderremote.domain.interactor.GetSendersInteractor;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.view.SenderListView;

public class SenderListPresenter implements Presenter<SenderListView> {

    private final int groupId;
    private final GetSendersInteractor getSendersInteractor;
    private SenderListView view;

    @Inject
    public SenderListPresenter(@Named("groupId") int groupId, @NonNull GetSendersInteractor getSendersInteractor) {
        this.groupId = groupId;
        this.getSendersInteractor = getSendersInteractor;
    }

    @Override
    public void attach(@NonNull SenderListView view) {
        this.view = view;
        view.setSenders(getSendersInteractor.execute());
    }

    @Override
    public void detach() {
        view = null;
    }

    public void onSenderClicked(@NonNull Sender sender) {
        view.showSender(sender);
    }

    public void onCreateSenderClicked() {
        view.navigateToCreateSender(groupId);
    }
}