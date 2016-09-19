package io.kindstrom.senderremote.presentation.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class CommandModule {

    private final int senderId;

    public CommandModule(int senderId) {
        this.senderId = senderId;
    }

    @Provides
    @Named("senderId")
    int provideSenderId() {
        return senderId;
    }
}
