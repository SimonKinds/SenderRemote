package io.kindstrom.senderremote.presentation.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.presentation.internal.di.PerGroup;

@Module
public class GroupModule {
    private final int groupId;

    public GroupModule() {
        groupId = -1;
    }

    public GroupModule(int groupId) {
        this.groupId = groupId;
    }

    @Provides
    @PerGroup
    @Named("groupId")
    int provideGroupId() {
        return groupId;
    }
}
