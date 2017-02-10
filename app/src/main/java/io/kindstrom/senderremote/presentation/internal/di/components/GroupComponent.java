package io.kindstrom.senderremote.presentation.internal.di.components;

import dagger.Component;
import io.kindstrom.senderremote.presentation.internal.di.PerGroup;
import io.kindstrom.senderremote.presentation.internal.di.modules.GroupModule;
import io.kindstrom.senderremote.presentation.view.activity.GroupCreateActivity;
import io.kindstrom.senderremote.presentation.view.activity.GroupListActivity;

@PerGroup
@Component(modules = GroupModule.class, dependencies = ApplicationComponent.class)
public interface GroupComponent {
    void inject(GroupListActivity groupListActivity);

    void inject(GroupCreateActivity groupCreateActivity);
}
