package io.kindstrom.senderremote.presentation.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.database.GroupRepositoryImpl;
import io.kindstrom.senderremote.domain.database.GroupRepository;

@Module
public class GroupModule {
    @Provides
    static GroupRepository providesGroupRepository(GroupRepositoryImpl groupRepository) {
        return groupRepository;
    }
}
