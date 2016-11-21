package io.kindstrom.senderremote.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.kindstrom.senderremote.domain.executor.PostExecutionThread;
import io.kindstrom.senderremote.domain.executor.ThreadExecutor;
import io.kindstrom.senderremote.domain.messaging.ResponseReceiver;

import static org.mockito.Mockito.verify;

public class ReceiveResponseInteractorTest {
    @Mock
    private ThreadExecutor threadExecutor;
    @Mock
    private PostExecutionThread postExecutionThread;
    @Mock
    private ResponseReceiver responseReceiver;

    private ReceiveResponseInteractor interactor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        interactor = new ReceiveResponseInteractor(threadExecutor, postExecutionThread,
                responseReceiver);
    }

    @Test
    public void buildUseCaseObservableListen() throws Exception {
        interactor.buildUseCaseObservable();

        verify(responseReceiver).listen();
    }
}