package io.kindstrom.senderremote.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.kindstrom.senderremote.R;
import io.kindstrom.senderremote.domain.model.Sender;
import io.kindstrom.senderremote.presentation.internal.di.components.DaggerGroupComponent;
import io.kindstrom.senderremote.presentation.presenter.GroupCreatePresenter;
import io.kindstrom.senderremote.presentation.view.GroupCreateView;


public class GroupCreateActivity extends BaseActivity implements GroupCreateView {

    @BindView(R.id.et_group_name)
    EditText groupNameView;
    @BindView(R.id.tv_sender_selection_title)
    TextView senderSelectionTitle;
    @BindView(R.id.gl_senders)
    GridLayout senderSelectionView;

    @Inject
    GroupCreatePresenter presenter;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, GroupCreateActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_create);

        if (savedInstanceState == null) {
            inject();
            ButterKnife.bind(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.attach(this);
    }

    void inject() {
        DaggerGroupComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    protected void onPause() {
        presenter.detach();
        super.onPause();
    }

    @OnClick(R.id.bt_create_group)
    public void onCreateButtonClicked() {
        presenter.onCreateButtonClicked(groupNameView.getText().toString(), getSelectedMemberIds());
    }

    @Override
    public void returnToPreviousView() {
        finish();
    }

    @Override
    public void showSenders(List<Sender> senders) {
        senderSelectionTitle.setVisibility(View.VISIBLE);
        senderSelectionView.setVisibility(View.VISIBLE);

        senderSelectionView.removeAllViews();
        addSendersToView(senders);
    }

    private void addSendersToView(List<Sender> senders) {
        GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED);
        GridLayout.Spec colSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        for (Sender s : senders) {
            CheckBox c = new AppCompatCheckBox(this);
            c.setLayoutParams(new GridLayout.LayoutParams(rowSpec, colSpec));
            c.setTag(s.getId());
            c.setText(s.getName());

            senderSelectionView.addView(c);
        }
    }

    private List<Integer> getSelectedMemberIds() {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < senderSelectionView.getChildCount(); ++i) {
            if (((CheckBox) senderSelectionView.getChildAt(i)).isChecked()) {
                ids.add((Integer) senderSelectionView.getChildAt(i).getTag());
            }
        }

        return ids;
    }

    @Override
    public void hideSenderSelectionView() {
        senderSelectionTitle.setVisibility(View.GONE);
        senderSelectionView.setVisibility(View.GONE);
    }
}
