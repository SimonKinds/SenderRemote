package io.kindstrom.senderremote.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.kindstrom.senderremote.R;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.adapter.SingleTextItemAdapter;
import io.kindstrom.senderremote.presentation.internal.di.components.DaggerSenderComponent;
import io.kindstrom.senderremote.presentation.internal.di.modules.SenderModule;
import io.kindstrom.senderremote.presentation.presenter.SenderListPresenter;
import io.kindstrom.senderremote.presentation.view.SenderListView;


public class SenderListActivity extends BaseActivity implements SenderListView {
    private static final String INTENT_EXTRA_GROUP_ID = "group_id";
    @BindView(R.id.rv)
    RecyclerView rv;
    @Inject
    SenderListPresenter presenter;

    public static Intent getCallingIntent(Context context, int groupId) {
        Intent intent = new Intent(context, SenderListActivity.class);
        intent.putExtra(INTENT_EXTRA_GROUP_ID, groupId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        int groupId = getIntent().getExtras().getInt(INTENT_EXTRA_GROUP_ID);

        DaggerSenderComponent.builder()
                .applicationComponent(getApplicationComponent())
                .senderModule(new SenderModule(groupId))
                .build()
                .inject(this);

        ButterKnife.bind(this);

        rv.setLayoutManager(new LinearLayoutManager(this));

        presenter.attach(this);
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
    public void setSenders(List<Sender> senders) {
        rv.setAdapter(new SingleTextItemAdapter<Sender>(senders) {
            @Override
            protected String getText(Sender sender) {
                return sender.getName();
            }

            @Override
            protected void onItemClicked(Sender sender) {
                presenter.onSenderClicked(sender);
            }
        });
    }

    @Override
    public void showSender(Sender sender) {
        startActivity(CommandListActivity.getCallingIntent(this, sender.getId()));
    }
}
