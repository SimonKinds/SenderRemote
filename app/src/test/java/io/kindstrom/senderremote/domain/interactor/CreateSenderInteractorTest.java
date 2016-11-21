package io.kindstrom.senderremote.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Pin;
import io.kindstrom.senderremote.domain.model.Port;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.domain.model.command.OnCommand;
import io.kindstrom.senderremote.domain.model.command.StatusCommand;
import io.kindstrom.senderremote.domain.repository.CommandRepository;
import io.kindstrom.senderremote.domain.repository.GroupMemberRepository;
import io.kindstrom.senderremote.domain.repository.InputRepository;
import io.kindstrom.senderremote.domain.repository.OutputRepository;
import io.kindstrom.senderremote.domain.repository.SenderRepository;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateSenderInteractorTest {

    private final int EXPECTED_SENDER_ID = 15;

    private final int EXPECTED_INPUT_ID_1 = 4;
    private final int EXPECTED_INPUT_ID_2 = 10;

    private final int EXPECTED_OUTPUT_ID_1 = 5;
    private final int EXPECTED_OUTPUT_ID_2 = 18;

    private final int EXPECTED_COMMAND_ID_1 = 1;
    private final int EXPECTED_COMMAND_ID_2 = 5;

    private final int EXPECTED_GROUP_MEMBER_ID_1 = 7;
    private final int EXPECTED_GROUP_MEMBER_ID_2 = 14;

    @Mock
    private SenderRepository senderRepository;
    @Mock
    private InputRepository inputRepository;
    @Mock
    private OutputRepository outputRepository;
    @Mock
    private CommandRepository commandRepository;
    @Mock
    private GroupMemberRepository groupMemberRepository;

    private String name = "name";
    private String number = "0704806052";
    private Pin pin = Pin.create("1234");
    private List<Command> commands;
    private List<Port> inputs;
    private List<Port> outputs;

    private List<Integer> groupIds;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        inputs = new ArrayList<>();
        inputs.add(new Port(-1, 4, "input number 4"));
        inputs.add(new Port(-1, 10, "another input"));

        outputs = new ArrayList<>();
        outputs.add(new Port(-1, 7, "output number 7"));
        outputs.add(new Port(-1, 3, "another output"));

        commands = new ArrayList<>();
        commands.add(new OnCommand(-1, "on", "turn on"));
        commands.add(new StatusCommand(-1, "status", "get port status"));

        groupIds = new ArrayList<>();
        groupIds.add(EXPECTED_GROUP_MEMBER_ID_1);
        groupIds.add(EXPECTED_GROUP_MEMBER_ID_2);

        when(senderRepository.insert(any()))
                .thenReturn(EXPECTED_SENDER_ID);
        when(inputRepository.insert(anyInt(), any()))
                .thenReturn(EXPECTED_INPUT_ID_1)
                .thenReturn(EXPECTED_INPUT_ID_2);
        when(outputRepository.insert(anyInt(), any()))
                .thenReturn(EXPECTED_OUTPUT_ID_1)
                .thenReturn(EXPECTED_OUTPUT_ID_2);
        when(commandRepository.insert(anyInt(), any()))
                .thenReturn(EXPECTED_COMMAND_ID_1)
                .thenReturn(EXPECTED_COMMAND_ID_2);
        when(groupMemberRepository.insert(anyInt(), anyInt()))
                .thenReturn(EXPECTED_GROUP_MEMBER_ID_1)
                .thenReturn(EXPECTED_GROUP_MEMBER_ID_2);
    }

    @Test
    public void executeWithAllParameters() throws Exception {
        Sender sender = new Sender(-1, name, number, pin, inputs, outputs, commands);
        CreateSenderInteractor interactor =
                new CreateSenderInteractor(senderRepository, inputRepository, outputRepository,
                        commandRepository, groupMemberRepository, sender, groupIds);

        assertEquals(EXPECTED_SENDER_ID, (int) interactor.execute());

        verify(senderRepository).insert(sender);

        verify(inputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(0));
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(1));

        verify(outputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(0));
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(1));

        verify(commandRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(0));
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(1));

        verify(groupMemberRepository, times(2)).insert(anyInt(), eq(EXPECTED_SENDER_ID));
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_1, EXPECTED_SENDER_ID);
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_2, EXPECTED_SENDER_ID);
    }

    @Test
    public void executeWithNoGroups() throws Exception {
        Sender sender = new Sender(-1, name, number, pin, inputs, outputs, commands);
        CreateSenderInteractor interactor =
                new CreateSenderInteractor(senderRepository, inputRepository, outputRepository,
                        commandRepository, groupMemberRepository, sender, null);

        assertEquals(EXPECTED_SENDER_ID, (int) interactor.execute());

        verify(senderRepository).insert(sender);

        verify(inputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(0));
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(1));

        verify(outputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(0));
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(1));

        verify(commandRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(0));
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(1));

        verify(groupMemberRepository, never()).insert(anyInt(), anyInt());
    }

    @Test
    public void executeWithNoInputs() throws Exception {
        Sender sender = new Sender(-1, name, number, pin, null, outputs, commands);
        CreateSenderInteractor interactor =
                new CreateSenderInteractor(senderRepository, inputRepository, outputRepository,
                        commandRepository, groupMemberRepository, sender, groupIds);

        assertEquals(EXPECTED_SENDER_ID, (int) interactor.execute());

        verify(senderRepository).insert(sender);

        verify(outputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(0));
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(1));

        verify(commandRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(0));
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(1));

        verify(groupMemberRepository, times(2)).insert(anyInt(), eq(EXPECTED_SENDER_ID));
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_1, EXPECTED_SENDER_ID);
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_2, EXPECTED_SENDER_ID);
    }

    @Test
    public void executeWith() throws Exception {
        Sender sender = new Sender(-1, name, number, pin, inputs, outputs, commands);
        CreateSenderInteractor interactor =
                new CreateSenderInteractor(senderRepository, inputRepository, outputRepository,
                        commandRepository, groupMemberRepository, sender, groupIds);

        assertEquals(EXPECTED_SENDER_ID, (int) interactor.execute());

        verify(senderRepository).insert(sender);

        verify(inputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(0));
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(1));

        verify(outputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(0));
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(1));

        verify(commandRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(0));
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(1));

        verify(groupMemberRepository, times(2)).insert(anyInt(), eq(EXPECTED_SENDER_ID));
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_1, EXPECTED_SENDER_ID);
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_2, EXPECTED_SENDER_ID);
    }

    @Test
    public void executeWithNoOutput() throws Exception {
        Sender sender = new Sender(-1, name, number, pin, inputs, outputs, commands);
        CreateSenderInteractor interactor =
                new CreateSenderInteractor(senderRepository, inputRepository, outputRepository,
                        commandRepository, groupMemberRepository, sender, groupIds);

        assertEquals(EXPECTED_SENDER_ID, (int) interactor.execute());

        verify(senderRepository).insert(sender);

        verify(inputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(0));
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(1));

        verify(commandRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(0));
        verify(commandRepository).insert(EXPECTED_SENDER_ID, commands.get(1));

        verify(groupMemberRepository, times(2)).insert(anyInt(), eq(EXPECTED_SENDER_ID));
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_1, EXPECTED_SENDER_ID);
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_2, EXPECTED_SENDER_ID);
    }

    @Test
    public void executeWithNoCommands() throws Exception {
        Sender sender = new Sender(-1, name, number, pin, inputs, outputs, null);
        CreateSenderInteractor interactor =
                new CreateSenderInteractor(senderRepository, inputRepository, outputRepository,
                        commandRepository, groupMemberRepository, sender, groupIds);

        assertEquals(EXPECTED_SENDER_ID, (int) interactor.execute());

        verify(senderRepository).insert(sender);

        verify(inputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(0));
        verify(inputRepository).insert(EXPECTED_SENDER_ID, inputs.get(1));

        verify(outputRepository, times(2)).insert(eq(EXPECTED_SENDER_ID), any());
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(0));
        verify(outputRepository).insert(EXPECTED_SENDER_ID, outputs.get(1));

        verify(groupMemberRepository, times(2)).insert(anyInt(), eq(EXPECTED_SENDER_ID));
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_1, EXPECTED_SENDER_ID);
        verify(groupMemberRepository).insert(EXPECTED_GROUP_MEMBER_ID_2, EXPECTED_SENDER_ID);
    }
}