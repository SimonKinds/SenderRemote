package io.kindstrom.senderremote.presentation.internal.di.components;

import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Component;
import io.kindstrom.senderremote.domain.database.CommandRepository;
import io.kindstrom.senderremote.domain.database.GroupMemberRepository;
import io.kindstrom.senderremote.domain.database.GroupRepository;
import io.kindstrom.senderremote.domain.database.InputRepository;
import io.kindstrom.senderremote.domain.database.OutputRepository;
import io.kindstrom.senderremote.domain.database.SenderRepository;
import io.kindstrom.senderremote.presentation.internal.di.modules.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    SQLiteDatabase db();

    GroupRepository groupRepository();

    GroupMemberRepository groupMemberRepository();

    SenderRepository senderRepository();

    InputRepository inputRepository();

    OutputRepository outputRepsository();

    CommandRepository commandRepository();
}
