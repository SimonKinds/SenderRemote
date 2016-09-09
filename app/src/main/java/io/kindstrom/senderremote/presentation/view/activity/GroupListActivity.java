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
import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.presentation.adapter.SingleTextItemAdapter;
import io.kindstrom.senderremote.presentation.internal.di.components.DaggerGroupComponent;
import io.kindstrom.senderremote.presentation.presenter.GroupListPresenter;
import io.kindstrom.senderremote.presentation.view.GroupListView;


public class GroupListActivity extends BaseActivity implements GroupListView {
    @BindView(R.id.rv)
    RecyclerView rv_group_list;

    @Inject
    GroupListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        ButterKnife.bind(this);
        rv_group_list.setLayoutManager(new LinearLayoutManager(this));


        DaggerGroupComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);

        presenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
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
        Snackbar.make(rv_group_list, "Viewing group " + group.getName(), Snackbar.LENGTH_SHORT).show();
    }
}
