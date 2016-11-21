package io.kindstrom.senderremote.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.domain.repository.GroupMemberRepository;
import io.kindstrom.senderremote.domain.repository.GroupRepository;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateGroupInteractorTest {

    @Mock
    GroupRepository groupRepository;
    @Mock
    GroupMemberRepository groupMemberRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeNoMembers() throws Exception {
        Group group = new Group(-1, "Group name", null);

        int expectedGroupId = 2;

        when(groupRepository.insert(group))
                .thenReturn(expectedGroupId);

        CreateGroupInteractor interactor = new CreateGroupInteractor(groupRepository,
                groupMemberRepository, group);

        assertEquals(expectedGroupId, (int) interactor.execute());

        verify(groupRepository, times(1)).insert(group);
        verify(groupMemberRepository, times(0)).insert(anyInt(), anyInt());
    }

    @Test
    public void executeWithMembers() throws Exception {
        List<Sender> senders = new ArrayList<>();
        senders.add(new Sender(2, "name", "number", null, null, null, null));
        senders.add(new Sender(6, "name", "number", null, null, null, null));

        Group group = new Group(-1, "Group name", senders);

        int expectedGroupId = 2;

        when(groupRepository.insert(group))
                .thenReturn(expectedGroupId);

        when(groupMemberRepository.insert(anyInt(), anyInt()))
                .thenReturn(anyInt());

        CreateGroupInteractor interactor = new CreateGroupInteractor(groupRepository,
                groupMemberRepository, group);

        assertEquals(expectedGroupId, (int) interactor.execute());
        verify(groupRepository, times(1)).insert(group);
        verify(groupMemberRepository, times(2)).insert(anyInt(), anyInt());
    }
}