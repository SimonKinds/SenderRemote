package io.kindstrom.senderremote.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.domain.repository.SenderRepository;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllSendersInteractorTest {
    @Mock
    private SenderRepository senderRepository;

    private List<Sender> expectedSenders;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        expectedSenders = new ArrayList<>();
        expectedSenders.add(new Sender(1, "name", "number", null, null, null, null));
        expectedSenders.add(new Sender(10, "name", "number", null, null, null, null));

        when(senderRepository.getAll())
                .thenReturn(expectedSenders);
    }

    @Test
    public void execute() throws Exception {
        GetAllSendersInteractor interactor = new GetAllSendersInteractor(senderRepository);

        assertEquals(expectedSenders, interactor.execute());
        verify(senderRepository).getAll();
    }

}