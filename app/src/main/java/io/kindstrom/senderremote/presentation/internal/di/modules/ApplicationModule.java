package io.kindstrom.senderremote.presentation.internal.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
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
import io.kindstrom.senderremote.data.executor.JobExecutor;
import io.kindstrom.senderremote.data.resource.DefaultCommandsRepositoryImpl;
import io.kindstrom.senderremote.domain.executor.PostExecutionThread;
import io.kindstrom.senderremote.domain.executor.ThreadExecutor;
import io.kindstrom.senderremote.domain.repository.CommandRepository;
import io.kindstrom.senderremote.domain.repository.DefaultCommandsRepository;
import io.kindstrom.senderremote.domain.repository.GroupMemberRepository;
import io.kindstrom.senderremote.domain.repository.GroupRepository;
import io.kindstrom.senderremote.domain.repository.InputRepository;
import io.kindstrom.senderremote.domain.repository.OutputRepository;
import io.kindstrom.senderremote.domain.repository.SenderRepository;
import io.kindstrom.senderremote.presentation.UIThread;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    static ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    static PostExecutionThread provideUiThread(UIThread uiThread) {
        return uiThread;
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

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    DefaultCommandsRepository provideDefaultCommandsRepository(DefaultCommandsRepositoryImpl defaultCommandsRepository) {
        return defaultCommandsRepository;
    }
}
