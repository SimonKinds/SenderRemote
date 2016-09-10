package io.kindstrom.senderremote.domain.interactor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import io.kindstrom.senderremote.domain.database.GroupMemberRepository;
import io.kindstrom.senderremote.domain.database.SenderRepository;
import io.kindstrom.senderremote.domain.model.Sender;

public class GetSendersInteractor implements Interactor<List<Sender>> {
    private final int groupId;
    private final SenderRepository senderRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Inject
    public GetSendersInteractor(@Named("groupId") int groupId, SenderRepository senderRepository, GroupMemberRepository groupMemberRepository) {
        this.groupId = groupId;
        this.senderRepository = senderRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    @Override
    public List<Sender> execute() {
        Set<Integer> memberIds = new HashSet<>(groupMemberRepository.get(groupId));
        List<Sender> allSenders = senderRepository.getAll();
        List<Sender> members = new ArrayList<>();
        for (Sender sender : allSenders) {
            if (memberIds.contains(sender.getId())) {
                members.add(sender);
            }
        }
        return members;
    }
}
