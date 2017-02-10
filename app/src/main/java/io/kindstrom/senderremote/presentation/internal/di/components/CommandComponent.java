package io.kindstrom.senderremote.presentation.internal.di.components;

import dagger.Component;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.internal.di.modules.CommandModule;

@PerActivity
@Component(modules = CommandModule.class, dependencies = SenderComponent.class)
public interface CommandComponent {
}
