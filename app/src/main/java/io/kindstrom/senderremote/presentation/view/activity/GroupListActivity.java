package io.kindstrom.senderremote.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.kindstrom.senderremote.R;
import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.presentation.adapter.SingleTextItemAdapter;
import io.kindstrom.senderremote.presentation.internal.di.components.DaggerGroupComponent;
import io.kindstrom.senderremote.presentation.presenter.GroupListPresenter;
import io.kindstrom.senderremote.presentation.view.GroupListView;


public class GroupListActivity extends BaseActivity implements GroupListView {
    private final static int REQUEST_CODE_CREATE_GROUP = 1;

    @BindView(R.id.rv)
    RecyclerView rv_group_list;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    GroupListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        ButterKnife.bind(this);

        DaggerGroupComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);

        setupUi();
    }

    private void setupUi() {
        setSupportActionBar(toolbar);
        rv_group_list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.attach(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                presenter.onCreateGroupClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        presenter.detach();

        super.onPause();
    }

    @Override
    public void setGroups(List<Group> groups) {
        rv_group_list.setAdapter(new SingleTextItemAdapter<Group>(groups) {
            @Override
            protected String getText(Group group) {
                return group.getName();
            }

            @Override
            protected void onItemClicked(Group group) {
                presenter.onGroupClicked(group);
            }
        });
    }

    @Override
    public void showGroup(Group group) {
        startActivity(SenderListActivity.getCallingIntent(this, group.getId()));
    }

    @Override
    public void showCreateGroupView() {
        startActivityForResult(GroupCreateActivity.getCallingIntent(this), REQUEST_CODE_CREATE_GROUP);
    }
}
