package io.kindstrom.senderremote.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Port;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.domain.repository.CommandRepository;
import io.kindstrom.senderremote.domain.repository.GroupMemberRepository;
import io.kindstrom.senderremote.domain.repository.InputRepository;
import io.kindstrom.senderremote.domain.repository.OutputRepository;
import io.kindstrom.senderremote.domain.repository.SenderRepository;

public class CreateSenderInteractor implements Interactor<Integer> {
    private final SenderRepository senderRepository;
    private final InputRepository inputRepository;
    private final OutputRepository outputRepository;
    private final CommandRepository commandRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final Sender sender;
    private final List<Integer> groups;

    @Inject
    public CreateSenderInteractor(SenderRepository senderRepository,
                                  InputRepository inputRepository, OutputRepository outputRepository,
                                  CommandRepository commandRepository,
                                  GroupMemberRepository groupMemberRepository, Sender sender, List<Integer> groups) {
        this.senderRepository = senderRepository;
        this.inputRepository = inputRepository;
        this.outputRepository = outputRepository;
        this.commandRepository = commandRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.sender = sender;
        this.groups = groups;
    }

    @Override
    public Integer execute() {
        int senderId = senderRepository.insert(sender);

        Sender sender = new Sender(senderId,
                this.sender.getName(),
                this.sender.getNumber(),
                this.sender.getPin(),
                this.sender.getInputs(),
                this.sender.getOutputs(),
                this.sender.getCommands());

        insertInputs(sender);
        insertOutputs(sender);
        insertCommands(sender);
        insertGroupMembers(sender);
        return senderId;
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
        for (Integer g : groups) {
            groupMemberRepository.insert(g, sender.getId());
        }
    }
}
