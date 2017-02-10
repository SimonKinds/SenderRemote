package io.kindstrom.senderremote.presentation.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.presentation.internal.di.PerCommand;

@Module
public class CommandModule {

    private final int commandId;

    public CommandModule(int commandId) {
        this.commandId = commandId;
    }

    @Provides
    @PerCommand
    @Named("commandId")
    int provideCommandId() {
        return commandId;
    }
}
