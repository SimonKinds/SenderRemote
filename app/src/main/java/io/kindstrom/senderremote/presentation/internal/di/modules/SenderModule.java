package io.kindstrom.senderremote.presentation.internal.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.database.GroupMemberRepositoryImpl;
import io.kindstrom.senderremote.data.database.SenderRepositoryImpl;
import io.kindstrom.senderremote.domain.database.GroupMemberRepository;
import io.kindstrom.senderremote.domain.database.SenderRepository;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;

@Module
public class SenderModule {
    private final int groupId;

    public SenderModule(int groupId) {
        this.groupId = groupId;
    }

    @Provides
    @PerActivity
    static SenderRepository providesSenderRepository(SenderRepositoryImpl senderRepository) {
        return senderRepository;
    }

    @Provides
    @PerActivity
    static GroupMemberRepository provideGroupMemberRepository(GroupMemberRepositoryImpl groupMemberRepository) {
        return groupMemberRepository;
    }

    @Provides
    @Named("groupId")
    int provideGroupId() {
        return groupId;
    }
}
