package io.kindstrom.senderremote.presentation;

import android.app.Application;

import io.kindstrom.senderremote.presentation.internal.di.components.ApplicationComponent;
import io.kindstrom.senderremote.presentation.internal.di.components.DaggerApplicationComponent;
import io.kindstrom.senderremote.presentation.internal.di.modules.ApplicationModule;


public class SenderRemoteApplication extends Application {
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
