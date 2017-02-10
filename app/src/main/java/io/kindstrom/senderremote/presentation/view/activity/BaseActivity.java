package io.kindstrom.senderremote.presentation.view.activity;

import android.support.v7.app.AppCompatActivity;

import io.kindstrom.senderremote.presentation.SenderRemoteApplication;
import io.kindstrom.senderremote.presentation.internal.di.components.ApplicationComponent;
import io.kindstrom.senderremote.presentation.internal.di.components.GroupComponent;


public abstract class BaseActivity extends AppCompatActivity {

    public final ApplicationComponent getApplicationComponent() {
        return getCastedApplication().getApplicationComponent();
    }

    public final GroupComponent getGroupComponent() {
        return getCastedApplication().getGroupComponent();
    }

    public final void setGroupComponent(GroupComponent groupComponent) {
        getCastedApplication().setGroupComponent(groupComponent);
    }

    private SenderRemoteApplication getCastedApplication() {
        return (SenderRemoteApplication) getApplication();
    }
}
