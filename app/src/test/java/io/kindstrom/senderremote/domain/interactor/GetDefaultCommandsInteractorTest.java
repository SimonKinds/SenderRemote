package io.kindstrom.senderremote.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.command.OnCommand;
import io.kindstrom.senderremote.domain.model.command.PinCommand;
import io.kindstrom.senderremote.domain.model.command.StatusCommand;
import io.kindstrom.senderremote.domain.repository.DefaultCommandsRepository;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetDefaultCommandsInteractorTest {
    @Mock
    private DefaultCommandsRepository defaultCommandsRepository;

    private List<Command> commands = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        commands = new ArrayList<>();
        commands.add(new OnCommand(-1, "on", "turn on"));
        commands.add(new StatusCommand(-1, "status", "get port status"));
        commands.add(new PinCommand(-1, "pin", "update pin"));

        when(defaultCommandsRepository.getDefaultCommands())
                .thenReturn(commands);
    }

    @Test
    public void execute() throws Exception {
        GetDefaultCommandsInteractor interactor = new
                GetDefaultCommandsInteractor(defaultCommandsRepository);

        assertEquals(commands, interactor.execute());
        verify(defaultCommandsRepository).getDefaultCommands();
    }

}