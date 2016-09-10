package io.kindstrom.senderremote.presentation.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.database.SenderRepositoryImpl;
import io.kindstrom.senderremote.domain.database.SenderRepository;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;

@Module
public class SenderModule {
    @Provides
    @PerActivity
    static SenderRepository providesSenderRepository(SenderRepositoryImpl senderRepository) {
        return senderRepository;
    }
}
