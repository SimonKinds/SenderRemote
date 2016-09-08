package io.kindstrom.senderremote.presentation.presenter;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.interactor.GetGroupsInteractor;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.view.GroupListView;

@PerActivity
public class GroupListPresenter {
    private final GetGroupsInteractor getGroupsInteractor;
    private GroupListView view;

    @Inject
    public GroupListPresenter(GetGroupsInteractor getGroupsInteractor) {
        this.getGroupsInteractor = getGroupsInteractor;
    }

    public void attach(GroupListView groupListView) {
        groupListView.setGroups(getGroupsInteractor.execute());
    }

    public void detach() {
        //nop
    }
}
