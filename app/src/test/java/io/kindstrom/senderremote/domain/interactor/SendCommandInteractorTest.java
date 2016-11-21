package io.kindstrom.senderremote.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.kindstrom.senderremote.domain.executor.PostExecutionThread;
import io.kindstrom.senderremote.domain.executor.ThreadExecutor;
import io.kindstrom.senderremote.domain.messaging.CommandSender;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class SendCommandInteractorTest {
    @Mock
    ThreadExecutor threadExecutor;
    @Mock
    PostExecutionThread postExecutionThread;
    @Mock
    private CommandSender commandSender;

    private SendCommandInteractor interactor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        interactor = new SendCommandInteractor(threadExecutor,
                postExecutionThread, commandSender);
    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        String arg = "ON 1,2 1234";
        interactor.buildUseCaseObservable(arg);

        verify(commandSender).send(arg);
        verifyNoMoreInteractions(commandSender);
    }
}