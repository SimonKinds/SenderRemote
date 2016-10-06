package io.kindstrom.senderremote.presentation.view.activity;

import android.support.v7.app.AppCompatActivity;

import io.kindstrom.senderremote.presentation.SenderRemoteApplication;
import io.kindstrom.senderremote.presentation.internal.di.components.ApplicationComponent;


public abstract class BaseActivity extends AppCompatActivity {

    public final ApplicationComponent getApplicationComponent() {
        return ((SenderRemoteApplication) getApplication()).getApplicationComponent();
    }
}
