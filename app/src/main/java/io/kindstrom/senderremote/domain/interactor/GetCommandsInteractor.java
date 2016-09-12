package io.kindstrom.senderremote.domain.interactor;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.kindstrom.senderremote.domain.database.CommandRepository;
import io.kindstrom.senderremote.domain.model.Command;

public class GetCommandsInteractor implements Interactor<List<Command>> {

    private final int senderId;
    private final CommandRepository commandRepository;

    @Inject
    public GetCommandsInteractor(@Named("senderId") int senderId, CommandRepository commandRepository) {
        this.senderId = senderId;
        this.commandRepository = commandRepository;
    }

    @Override
    public List<Command> execute() {
        return commandRepository.getForSender(senderId);
    }
}
