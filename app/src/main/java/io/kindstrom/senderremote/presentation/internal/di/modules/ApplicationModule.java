package io.kindstrom.senderremote.presentation.internal.di.modules;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.kindstrom.senderremote.data.database.AppSQLiteOpenHelper;

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
    Context providesApplicationContext() {
        return application;
    }
}
