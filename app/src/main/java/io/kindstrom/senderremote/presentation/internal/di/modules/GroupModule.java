package io.kindstrom.senderremote.presentation.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.database.GroupRepositoryImpl;
import io.kindstrom.senderremote.domain.database.Repository;
import io.kindstrom.senderremote.domain.model.Group;

@Module
public class GroupModule {
    @Provides
    static Repository<Group> providesGroupRepository(GroupRepositoryImpl groupRepository) {
        return groupRepository;
    }
}
