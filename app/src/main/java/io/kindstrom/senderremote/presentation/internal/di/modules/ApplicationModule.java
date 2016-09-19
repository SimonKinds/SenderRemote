package io.kindstrom.senderremote.presentation.internal.di.modules;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.database.AppSQLiteOpenHelper;
import io.kindstrom.senderremote.data.database.CommandRepositoryImpl;
import io.kindstrom.senderremote.data.database.GroupMemberRepositoryImpl;
import io.kindstrom.senderremote.data.database.GroupRepositoryImpl;
import io.kindstrom.senderremote.data.database.InputRepositoryImpl;
import io.kindstrom.senderremote.data.database.OutputRepositoryImpl;
import io.kindstrom.senderremote.data.database.SenderRepositoryImpl;
import io.kindstrom.senderremote.domain.database.CommandRepository;
import io.kindstrom.senderremote.domain.database.GroupMemberRepository;
import io.kindstrom.senderremote.domain.database.GroupRepository;
import io.kindstrom.senderremote.domain.database.InputRepository;
import io.kindstrom.senderremote.domain.database.OutputRepository;
import io.kindstrom.senderremote.domain.database.SenderRepository;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    static SQLiteDatabase providesDatabase(AppSQLiteOpenHelper sqliteHelper) {
        return sqliteHelper.getWritableDatabase();
    }

    @Provides
    @Singleton
    static GroupRepository providesGroupRepository(GroupRepositoryImpl groupRepository) {
        return groupRepository;
    }

    @Provides
    @Singleton
    static SenderRepository providesSenderRepository(SenderRepositoryImpl senderRepository) {
        return senderRepository;
    }

    @Provides
    @Singleton
    static GroupMemberRepository provideGroupMemberRepository(GroupMemberRepositoryImpl groupMemberRepository) {
        return groupMemberRepository;
    }

    @Provides
    @Singleton
    static InputRepository provideInputRepository(InputRepositoryImpl inputRepository) {
        return inputRepository;
    }

    @Provides
    @Singleton
    static OutputRepository provideOutputRepository(OutputRepositoryImpl outputRepository) {
        return outputRepository;
    }

    @Provides
    @Singleton
    static CommandRepository providesCommandRepository(CommandRepositoryImpl commandRepository) {
        return commandRepository;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return application;
    }
}
