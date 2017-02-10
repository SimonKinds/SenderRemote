package io.kindstrom.senderremote.presentation.internal.di.components;

import javax.inject.Named;

import dagger.Component;
import io.kindstrom.senderremote.presentation.internal.di.PerGroup;
import io.kindstrom.senderremote.presentation.internal.di.modules.GroupModule;
import io.kindstrom.senderremote.presentation.view.activity.GroupCreateActivity;
import io.kindstrom.senderremote.presentation.view.activity.GroupListActivity;
import io.kindstrom.senderremote.presentation.view.activity.SenderCreateActivity;
import io.kindstrom.senderremote.presentation.view.activity.SenderListActivity;
import io.kindstrom.senderremote.presentation.view.fragment.InputNamingFragment;
import io.kindstrom.senderremote.presentation.view.fragment.OutputNamingFragment;

@PerGroup
@Component(modules = GroupModule.class, dependencies = ApplicationComponent.class)
public interface GroupComponent extends ApplicationComponent {
    void inject(GroupListActivity groupListActivity);

    void inject(GroupCreateActivity groupCreateActivity);

    void inject(SenderListActivity senderListActivity);

    void inject(SenderCreateActivity senderCreateActivity);

    void inject(InputNamingFragment inputNamingFragment);

    void inject(OutputNamingFragment outputNamingFragment);

    @Named("groupId")
    int groupId();
}
