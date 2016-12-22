package io.kindstrom.senderremote.presentation.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.domain.interactor.GetSenderInteractor;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;

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

    @Provides
    @PerActivity
    Sender provideSender(GetSenderInteractor getSenderInteractor) {
        return getSenderInteractor.execute();
    }
}
