package io.kindstrom.senderremote.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.kindstrom.senderremote.R;
import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.presentation.adapter.SingleTextItemAdapter;
import io.kindstrom.senderremote.presentation.internal.di.components.DaggerCommandComponent;
import io.kindstrom.senderremote.presentation.internal.di.modules.CommandModule;
import io.kindstrom.senderremote.presentation.presenter.CommandListPresenter;
import io.kindstrom.senderremote.presentation.view.CommandListView;


public class CommandListActivity extends BaseActivity implements CommandListView {
    private static final String INTENT_EXTRA_SENDER_ID = "sender_id";
    @BindView(R.id.rv)
    RecyclerView rv;
    @Inject
    CommandListPresenter presenter;

    public static Intent getCallingIntent(Context context, int senderId) {
        Intent intent = new Intent(context, CommandListActivity.class);
        intent.putExtra(INTENT_EXTRA_SENDER_ID, senderId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        inject();
        ButterKnife.bind(this);

        setupUi();

        presenter.attach(this);
    }

    private void setupUi() {
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void inject() {
        int senderId = getIntent().getExtras().getInt(INTENT_EXTRA_SENDER_ID);

        DaggerCommandComponent.builder()
                .applicationComponent(getApplicationComponent())
                .commandModule(new CommandModule(senderId))
                .build()
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void setCommands(List<Command> commands) {
        rv.setAdapter(new SingleTextItemAdapter<Command>(commands) {
            @Override
            protected String getText(Command command) {
                return command.getDescription();
            }

            @Override
            protected void onItemClicked(Command command) {
                presenter.onCommandClicked(command);
            }
        });
    }

    @Override
    public void showCommand(Command command) {
        Toast.makeText(this, "Showing " + command.getName(), Toast.LENGTH_SHORT).show();
    }
}
