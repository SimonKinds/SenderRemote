package io.kindstrom.senderremote.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Pin;
import io.kindstrom.senderremote.domain.model.Port;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.domain.repository.CommandRepository;
import io.kindstrom.senderremote.domain.repository.DefaultCommandsRepository;
import io.kindstrom.senderremote.domain.repository.GroupMemberRepository;
import io.kindstrom.senderremote.domain.repository.InputRepository;
import io.kindstrom.senderremote.domain.repository.OutputRepository;
import io.kindstrom.senderremote.domain.repository.SenderRepository;

public class CreateSenderInteractor implements Interactor<Integer> {
    private final SenderRepository senderRepository;
    private final InputRepository inputRepository;
    private final OutputRepository outputRepository;
    private final CommandRepository commandRepository;
    private final DefaultCommandsRepository defaultCommandsRepository;
    private final GroupMemberRepository groupMemberRepository;

    private boolean areParametersSet = false;
    private String name, number;
    private Pin pin;
    private String[] inputNames, outputNames;
    private List<Integer> groupIds;

    @Inject
    public CreateSenderInteractor(SenderRepository senderRepository,
                                  InputRepository inputRepository, OutputRepository outputRepository,
                                  CommandRepository commandRepository,
                                  DefaultCommandsRepository defaultCommandsRepository,
                                  GroupMemberRepository groupMemberRepository) {
        this.senderRepository = senderRepository;
        this.inputRepository = inputRepository;
        this.outputRepository = outputRepository;
        this.commandRepository = commandRepository;
        this.defaultCommandsRepository = defaultCommandsRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    @Override
    public Integer execute() {
        if (!areParametersSet) {
            throw new RuntimeException("Need to set the parameters before creating sender");
        }

        Sender sender = new Sender(-1, name, number, pin,
                createPortFromNames(inputNames),
                createPortFromNames(outputNames),
                defaultCommandsRepository.getDefaultCommands());

        int senderId = senderRepository.insert(sender);

        sender = new Sender(senderId, sender.getName(),
                sender.getNumber(),
                sender.getPin(),
                sender.getInputs(),
                sender.getOutputs(),
                sender.getCommands());

        insertInputs(sender);
        insertOutputs(sender);
        insertCommands(sender);
        insertGroupMembers(sender);
        return senderId;
    }


    private List<Port> createPortFromNames(String[] names) {
        List<Port> ports = new ArrayList<>();
        for (int i = 0; i < names.length; ++i) {
            ports.add(new Port(-1, i + 1, names[i]));
        }
        return ports;
    }

    private void insertCommands(Sender sender) {
        for (Command c : sender.getCommands()) {
            commandRepository.insert(sender.getId(), c);
        }
    }

    private void insertInputs(Sender sender) {
        for (Port p : sender.getInputs()) {
            inputRepository.insert(sender.getId(), p);
        }
    }

    private void insertOutputs(Sender sender) {
        for (Port p : sender.getOutputs()) {
            outputRepository.insert(sender.getId(), p);
        }
    }

    private void insertGroupMembers(Sender sender) {
        for (int i : groupIds) {
            groupMemberRepository.insert(i, sender.getId());
        }
    }

    public void setParameters(@NonNull String name, @NonNull String number,
                              @Nullable Pin pin, @NonNull String[] inputNames,
                              @NonNull String[] outputNames, @NonNull List<Integer> groupIds) {
        areParametersSet = true;

        this.name = name;
        this.number = number;
        this.pin = pin;
        this.inputNames = inputNames;
        this.outputNames = outputNames;
        this.groupIds = groupIds;
    }
}
