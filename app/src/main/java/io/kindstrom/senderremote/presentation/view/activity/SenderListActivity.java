package io.kindstrom.senderremote.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.kindstrom.senderremote.R;
import io.kindstrom.senderremote.domain.model.Command;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.adapter.SingleTextItemAdapter;
import io.kindstrom.senderremote.presentation.internal.di.components.DaggerSenderComponent;
import io.kindstrom.senderremote.presentation.presenter.SenderListPresenter;
import io.kindstrom.senderremote.presentation.view.SenderListView;


public class SenderListActivity extends BaseActivity implements SenderListView {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Inject
    SenderListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        DaggerSenderComponent.builder()
                .applicationComponent(getApplicationComponent())
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
        });
    }

    @Override
    public void showCommand(Command command) {
        Snackbar.make(rv, "Showing command", Snackbar.LENGTH_SHORT).show();
    }
}
