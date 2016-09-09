package io.kindstrom.senderremote.presentation.internal.di.components;

import dagger.Component;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.internal.di.modules.SenderModule;
import io.kindstrom.senderremote.presentation.view.activity.SenderListActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = SenderModule.class)
public interface SenderComponent {
    void inject(SenderListActivity senderListActivity);
}
