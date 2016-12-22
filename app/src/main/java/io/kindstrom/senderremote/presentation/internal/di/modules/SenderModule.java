package io.kindstrom.senderremote.presentation.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.messaging.CommandSenderImpl;
import io.kindstrom.senderremote.data.messaging.ResponseReceiverImpl;
import io.kindstrom.senderremote.domain.messaging.CommandSender;
import io.kindstrom.senderremote.domain.messaging.ResponseReceiver;

@Module
public class SenderModule {
    private final int groupId;

    public SenderModule() {
        groupId = -1;
    }

    public SenderModule(int groupId) {
        this.groupId = groupId;
    }

    @Provides
    static CommandSender provideCommandSender(CommandSenderImpl commandSender) {
        return commandSender;
    }

    @Provides
    static ResponseReceiver provideResponseReceiver(ResponseReceiverImpl responseReceiver) {
        return responseReceiver;
    }

    @Provides
    @Named("groupId")
    int provideGroupId() {
        return groupId;
    }
}
