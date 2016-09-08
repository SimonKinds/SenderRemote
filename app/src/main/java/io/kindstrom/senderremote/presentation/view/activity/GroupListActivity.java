package io.kindstrom.senderremote.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.kindstrom.senderremote.R;
import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.SenderRemoteApplication;
import io.kindstrom.senderremote.presentation.adapter.GroupAdapter;
import io.kindstrom.senderremote.presentation.internal.di.components.DaggerGroupComponent;
import io.kindstrom.senderremote.presentation.presenter.GroupListPresenter;
import io.kindstrom.senderremote.presentation.view.GroupListView;


public class GroupListActivity extends AppCompatActivity implements GroupListView {
    @BindView(R.id.rv_group_list)
    RecyclerView rv_group_list;

    @Inject
    GroupListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        ButterKnife.bind(this);
        rv_group_list.setLayoutManager(new LinearLayoutManager(this));


        DaggerGroupComponent.builder()
                .applicationComponent(((SenderRemoteApplication) getApplication()).getApplicationComponent())
                .build()
                .inject(this);

        presenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setGroups(List<Group> groups) {
        rv_group_list.setAdapter(new GroupAdapter(groups));
    }

    @Override
    public void viewSender(Sender sender) {
        Snackbar.make(rv_group_list, "Viewing sender", Snackbar.LENGTH_SHORT).show();
    }
}
