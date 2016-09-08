package io.kindstrom.senderremote.presentation.internal.di.components;

import dagger.Component;
import io.kindstrom.senderremote.presentation.internal.di.PerActivity;
import io.kindstrom.senderremote.presentation.internal.di.modules.GroupModule;
import io.kindstrom.senderremote.presentation.view.activity.GroupListActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = GroupModule.class)
public interface GroupComponent {
    void inject(GroupListActivity groupListActivity);
}
