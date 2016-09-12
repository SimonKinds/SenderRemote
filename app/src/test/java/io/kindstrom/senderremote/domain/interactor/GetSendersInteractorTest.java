package io.kindstrom.senderremote.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.domain.database.GroupMemberRepository;
import io.kindstrom.senderremote.domain.database.SenderRepository;
import io.kindstrom.senderremote.domain.model.Sender;

import static org.junit.Assert.assertEquals;

public class GetSendersInteractorTest {
    private final int groupId = 1;
    @Mock
    SenderRepository senderRepository;
    @Mock
    GroupMemberRepository groupMemberRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() throws Exception {
        List<Sender> allSenders = new ArrayList<>();
        allSenders.add(new Sender(1, "Sender 1", "1", null, null, null, null));
        allSenders.add(new Sender(2, "Sender 2", "1", null, null, null, null));
        allSenders.add(new Sender(3, "Sender 3", "1", null, null, null, null));
        Mockito.when(senderRepository.getAll()).thenReturn(allSenders);

        List<Integer> groupMembersIds = new ArrayList<>();
        groupMembersIds.add(1);
        groupMembersIds.add(3);
        Mockito.when(groupMemberRepository.get(groupId)).thenReturn(groupMembersIds);

        List<Sender> groupMembers = new ArrayList<>();
        groupMembers.add(allSenders.get(0));
        groupMembers.add(allSenders.get(2));

        GetSendersInteractor interactor = new GetSendersInteractor(groupId, senderRepository, groupMemberRepository);

        assertEquals(groupMembers, interactor.execute());
    }
}