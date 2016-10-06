package io.kindstrom.senderremote.domain.interactor;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.domain.repository.GroupMemberRepository;
import io.kindstrom.senderremote.domain.repository.GroupRepository;

public class CreateGroupInteractor implements Interactor<Integer> {
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    private final Group group;

    @Inject
    public CreateGroupInteractor(GroupRepository groupRepository, GroupMemberRepository groupMemberRepository, Group group) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.group = group;
    }

    @Override
    public Integer execute() {
        int groupId = groupRepository.insert(group);
        insertMembers(groupId);
        return groupId;
    }

    private void insertMembers(int groupId) {
        for (Sender s : group.getSenders()) {
            groupMemberRepository.insert(groupId, s.getId());
        }
    }
}
