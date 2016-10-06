package io.kindstrom.senderremote.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.command.LimitsCommand;
import io.kindstrom.senderremote.domain.model.command.MeasurementCommand;
import io.kindstrom.senderremote.domain.model.command.OffCommand;
import io.kindstrom.senderremote.domain.model.command.OnCommand;
import io.kindstrom.senderremote.domain.model.command.PinCommand;
import io.kindstrom.senderremote.domain.model.command.StatusCommand;
import io.kindstrom.senderremote.domain.repository.CommandRepository;

import static org.junit.Assert.assertEquals;

public class GetCommandsInteractorTest {
    private final int senderId1 = 1;
    private final int senderId2 = 2;
    @Mock
    CommandRepository commandRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() throws Exception {
        List<Command> sender1Commands = new ArrayList<>();
        sender1Commands.add(new OnCommand(1, "on", "desc"));
        sender1Commands.add(new OffCommand(2, "off", "desc2"));
        sender1Commands.add(new LimitsCommand(5, "limits", "desc3"));

        Mockito.when(commandRepository.getForSender(senderId1)).thenReturn(sender1Commands);

        List<Command> sender2Commands = new ArrayList<>();
        sender2Commands.add(new StatusCommand(4, "status", "desc"));
        sender2Commands.add(new PinCommand(3, "pin", "desc2"));
        sender2Commands.add(new MeasurementCommand(10, "meas", "desc3"));

        Mockito.when(commandRepository.getForSender(senderId2)).thenReturn(sender2Commands);

        GetCommandsInteractor interactor = new GetCommandsInteractor(senderId1, commandRepository);
        assertEquals(sender1Commands, interactor.execute());

        interactor = new GetCommandsInteractor(senderId2, commandRepository);
        assertEquals(sender2Commands, interactor.execute());
    }
}