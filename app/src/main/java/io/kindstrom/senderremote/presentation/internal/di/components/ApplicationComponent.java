package io.kindstrom.senderremote.presentation.internal.di.components;

import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Component;
import io.kindstrom.senderremote.domain.repository.CommandRepository;
import io.kindstrom.senderremote.domain.repository.DefaultCommandsRepository;
import io.kindstrom.senderremote.domain.repository.GroupMemberRepository;
import io.kindstrom.senderremote.domain.repository.GroupRepository;
import io.kindstrom.senderremote.domain.repository.InputRepository;
import io.kindstrom.senderremote.domain.repository.OutputRepository;
import io.kindstrom.senderremote.domain.repository.SenderRepository;
import io.kindstrom.senderremote.presentation.internal.di.modules.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    SQLiteDatabase db();

    Resources resources();

    GroupRepository groupRepository();

    GroupMemberRepository groupMemberRepository();

    SenderRepository senderRepository();

    InputRepository inputRepository();

    OutputRepository outputRepository();

    CommandRepository commandRepository();

    DefaultCommandsRepository defaultCommandRepository();
}
