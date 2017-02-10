package io.kindstrom.senderremote.presentation.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.messaging.CommandSenderImpl;
import io.kindstrom.senderremote.data.messaging.ResponseReceiverImpl;
import io.kindstrom.senderremote.domain.interactor.GetSenderInteractor;
import io.kindstrom.senderremote.domain.messaging.CommandSender;
import io.kindstrom.senderremote.domain.messaging.ResponseReceiver;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerSender;

@Module
public class SenderModule {
    private final int senderId;

    public SenderModule(int senderId) {
        this.senderId = senderId;
    }

    @Provides
    @PerSender
    static CommandSender provideCommandSender(CommandSenderImpl commandSender) {
        return commandSender;
    }

    @Provides
    @PerSender
    static ResponseReceiver provideResponseReceiver(ResponseReceiverImpl responseReceiver) {
        return responseReceiver;
    }

    @Provides
    @PerSender
    @Named("senderId")
    int provideSenderId() {
        return senderId;
    }

    @Provides
    @PerSender
    Sender provideSender(GetSenderInteractor getSenderInteractor) {
        return getSenderInteractor.execute();
    }
}
