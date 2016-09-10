package io.kindstrom.senderremote.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.database.SenderRepository;
import io.kindstrom.senderremote.domain.model.Sender;

public class GetSendersInteractor implements Interactor<List<Sender>> {
    private final SenderRepository senderRepository;

    @Inject
    public GetSendersInteractor(SenderRepository senderRepository) {
        this.senderRepository = senderRepository;
    }

    @Override
    public List<Sender> execute() {
        return senderRepository.getAll();
    }
}
