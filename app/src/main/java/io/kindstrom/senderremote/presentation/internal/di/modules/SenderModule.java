package io.kindstrom.senderremote.presentation.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.database.SenderRepository;
import io.kindstrom.senderremote.domain.database.Repository;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;

@Module
public class SenderModule {
    @Provides
    @PerActivity
    static Repository<Sender> providesSenderRepository(SenderRepository senderRepository) {
        return senderRepository;
    }
}
