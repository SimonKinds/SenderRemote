package io.kindstrom.senderremote.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.domain.repository.GroupRepository;

public class GetGroupsInteractor implements Interactor<List<Group>> {
    private final GroupRepository groupRepository;

    @Inject
    public GetGroupsInteractor(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> execute() {
        return groupRepository.getAll();
    }
}
