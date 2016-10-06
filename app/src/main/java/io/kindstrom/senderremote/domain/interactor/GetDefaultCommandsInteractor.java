package io.kindstrom.senderremote.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.repository.DefaultCommandsRepository;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;

@PerActivity
public class GetDefaultCommandsInteractor implements Interactor<List<Command>> {
    private final DefaultCommandsRepository defaultCommandsRepository;

    @Inject
    public GetDefaultCommandsInteractor(DefaultCommandsRepository defaultCommandsRepository) {
        this.defaultCommandsRepository = defaultCommandsRepository;
    }

    @Override
    public List<Command> execute() {
        return defaultCommandsRepository.getDefaultCommands();
    }
}
