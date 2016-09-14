package io.kindstrom.senderremote.domain.interactor;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.database.CommandRepository;
import io.kindstrom.senderremote.domain.database.InputRepository;
import io.kindstrom.senderremote.domain.database.OutputRepository;
import io.kindstrom.senderremote.domain.database.SenderRepository;
import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Port;
import io.kindstrom.senderremote.domain.model.Sender;

public class CreateSenderInteractor implements Interactor<Integer> {
    private final SenderRepository senderRepository;
    private final InputRepository inputRepository;
    private final OutputRepository outputRepository;
    private final CommandRepository commandRepository;

    private Sender sender;

    @Inject
    public CreateSenderInteractor(SenderRepository senderRepository,
                                  InputRepository inputRepository, OutputRepository outputRepository, CommandRepository commandRepository) {
        this.senderRepository = senderRepository;
        this.inputRepository = inputRepository;
        this.outputRepository = outputRepository;
        this.commandRepository = commandRepository;
    }

    @Override
    public Integer execute() {
        if (sender == null) {
            throw new RuntimeException("Need to set the sender");
        }

        int senderId = senderRepository.insert(sender);
        insertInputs(senderId);
        insertOutputs(senderId);
        insertCommands(senderId);
        return senderId;
    }

    private void insertCommands(int senderId) {
        for (Command c : sender.getCommands()) {
            commandRepository.insert(senderId, c);
        }
    }

    private void insertInputs(int senderId) {
        for (Port p : sender.getInputs()) {
            inputRepository.insert(senderId, p);
        }
    }

    private void insertOutputs(int senderId) {
        for (Port p : sender.getOutputs()) {
            outputRepository.insert(senderId, p);
        }
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
