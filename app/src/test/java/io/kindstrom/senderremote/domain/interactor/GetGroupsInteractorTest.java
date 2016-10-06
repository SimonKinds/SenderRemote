package io.kindstrom.senderremote.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.domain.repository.GroupRepository;

import static org.junit.Assert.assertEquals;

public class GetGroupsInteractorTest {
    @Mock
    GroupRepository groupRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() throws Exception {
        List<Group> allGroups = new ArrayList<>();
        allGroups.add(new Group(1, "group 1", null));
        allGroups.add(new Group(2, "group 2", null));

        Mockito.when(groupRepository.getAll()).thenReturn(allGroups);

        GetGroupsInteractor interactor = new GetGroupsInteractor(groupRepository);
        assertEquals(allGroups, interactor.execute());
    }

}