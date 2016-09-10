package io.kindstrom.senderremote.presentation.presenter;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.interactor.GetGroupsInteractor;
import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.view.GroupListView;

@PerActivity
public class GroupListPresenter implements Presenter<GroupListView> {
    private final GetGroupsInteractor getGroupsInteractor;
    private GroupListView view;

    @Inject
    public GroupListPresenter(GetGroupsInteractor getGroupsInteractor) {
        this.getGroupsInteractor = getGroupsInteractor;
    }

    @Override
    public void attach(GroupListView groupListView) {
        view = groupListView;
        groupListView.setGroups(getGroupsInteractor.execute());
    }

    @Override
    public void detach() {
        view = null;
    }

    public void onGroupClicked(Group group) {
        view.showGroup(group);
    }
}
