package io.kindstrom.senderremote.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.database.Repository;
import io.kindstrom.senderremote.domain.model.Group;

public class GetGroupsInteractor {
    private final Repository<Group> groupRepository;

    @Inject
    public GetGroupsInteractor(Repository<Group> groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> execute() {
        return groupRepository.getAll();
    }
}
