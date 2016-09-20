package io.kindstrom.senderremote.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.interactor.CreateGroupInteractor;
import io.kindstrom.senderremote.domain.interactor.GetAllSendersInteractor;
import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.view.GroupCreateView;

@PerActivity
public class GroupCreatePresenter implements Presenter<GroupCreateView> {
    private final GetAllSendersInteractor getAllSendersInteractor;
    private final CreateGroupInteractor createGroupInteractor;

    private GroupCreateView view;
    private List<Sender> allSenders;

    @Inject
    public GroupCreatePresenter(GetAllSendersInteractor getAllSendersInteractor,
                                CreateGroupInteractor createGroupInteractor) {
        this.getAllSendersInteractor = getAllSendersInteractor;
        this.createGroupInteractor = createGroupInteractor;
    }

    @Override
    public void attach(GroupCreateView view) {
        this.view = view;

        getSenders();
    }

    private void getSenders() {
        List<Sender> senders = getAllSendersInteractor.execute();
        this.allSenders = senders;

        if (senders.isEmpty()) {
            view.hideSenderSelectionView();
        } else {
            view.showSenders(senders);
        }
    }

    @Override
    public void detach() {
        view = null;
    }

    public void onCreateButtonClicked(String name, List<Integer> selectedMemberIds) {
        Group group = new Group(-1, name, getSelectedSenders(selectedMemberIds));

        createGroupInteractor.setGroup(group);
        createGroupInteractor.execute();

        view.returnToPreviousView();
    }

    private List<Sender> getSelectedSenders(List<Integer> selectedMemberIds) {
        List<Sender> members = new ArrayList<>(selectedMemberIds.size());
        for (Sender s : allSenders) {
            if (selectedMemberIds.contains(s.getId())) {
                members.add(s);
            }
        }
        return members;
    }
}
