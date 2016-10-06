package io.kindstrom.senderremote.presentation.internal.di.components;

import dagger.Component;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.internal.di.modules.SenderModule;
import io.kindstrom.senderremote.presentation.view.activity.SenderCreateActivity;
import io.kindstrom.senderremote.presentation.view.activity.SenderListActivity;
import io.kindstrom.senderremote.presentation.view.fragment.InputNamingFragment;
import io.kindstrom.senderremote.presentation.view.fragment.OutputNamingFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = SenderModule.class)
public interface SenderComponent {
    void inject(SenderListActivity senderListActivity);

    void inject(SenderCreateActivity senderCreateActivity);

    void inject(InputNamingFragment inputNamingFragment);

    void inject(OutputNamingFragment outputNamingFragment);
}
