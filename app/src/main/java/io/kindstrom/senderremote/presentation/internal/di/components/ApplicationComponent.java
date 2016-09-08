package io.kindstrom.senderremote.presentation.internal.di.components;

import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Component;
import io.kindstrom.senderremote.presentation.internal.di.modules.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    SQLiteDatabase db();
}
