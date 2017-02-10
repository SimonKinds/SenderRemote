package io.kindstrom.senderremote.presentation.internal.di.components;

import dagger.Component;
import io.kindstrom.senderremote.presentation.internal.di.PerSender;
import io.kindstrom.senderremote.presentation.internal.di.modules.SenderModule;
import io.kindstrom.senderremote.presentation.view.activity.CommandListActivity;

@PerSender
@Component(dependencies = GroupComponent.class, modules = SenderModule.class)
public interface SenderComponent extends GroupComponent {
    void inject(CommandListActivity activity);
}
