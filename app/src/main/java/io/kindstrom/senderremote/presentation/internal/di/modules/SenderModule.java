package io.kindstrom.senderremote.presentation.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

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
    @Named("groupId")
    int provideGroupId() {
        return groupId;
    }
}
