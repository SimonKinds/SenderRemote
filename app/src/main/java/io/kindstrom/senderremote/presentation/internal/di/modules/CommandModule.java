package io.kindstrom.senderremote.presentation.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.database.CommandRepositoryImpl;
import io.kindstrom.senderremote.domain.database.CommandRepository;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;

@Module
public class CommandModule {

    private final int senderId;

    public CommandModule(int senderId) {
        this.senderId = senderId;
    }

    @Provides
    @PerActivity
    static CommandRepository providesCommandRepository(CommandRepositoryImpl commandRepository) {
        return commandRepository;
    }

    @Provides
    @Named("senderId")
    int provideSenderId() {
        return senderId;
    }
}
