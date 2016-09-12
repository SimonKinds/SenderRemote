package io.kindstrom.senderremote.presentation.internal.di.components;

import dagger.Component;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.internal.di.modules.CommandModule;
import io.kindstrom.senderremote.presentation.view.activity.CommandListActivity;

@PerActivity
@Component(modules = CommandModule.class, dependencies = ApplicationComponent.class)
public interface CommandComponent {
    void inject(CommandListActivity activity);
}
