package io.kindstrom.senderremote.presentation.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.kindstrom.senderremote.R;
import io.kindstrom.senderremote.domain.model.Group;
import io.kindstrom.senderremote.presentation.presenter.SenderCreatePresenter;
import io.kindstrom.senderremote.presentation.util.PermissionHandler;
import io.kindstrom.senderremote.presentation.view.SenderCreateView;
import io.kindstrom.senderremote.presentation.view.dialog.PermissionRationaleDialogFragment;


public class SenderCreateActivity extends BaseActivity implements SenderCreateView,
        PermissionRationaleDialogFragment.PermissionCallback {

    public static final String RESULT_EXTRA_INPUT_NAMES = "input_names";
    public static final String RESULT_EXTRA_OUTPUT_NAMES = "output_names";

    private static final String INTENT_EXTRA_GROUP_ID = "group_id";
    private static final int REQUEST_CODE_PORT_NAMING = 1;

    private static final String RATIONALE_TAG = "rationale dialog";

    @BindView(R.id.til_sender_name)
    TextInputLayout til_name;
    @BindView(R.id.til_sender_number)
    TextInputLayout til_number;
    @BindView(R.id.til_sender_pin)
    TextInputLayout til_pin;
    @BindView(R.id.et_sender_name)
    EditText et_name;
    @BindView(R.id.et_sender_number)
    EditText et_number;
    @BindView(R.id.et_sender_pin)
    EditText et_pin;
    @BindView(R.id.gl_groups)
    GridLayout gl_groups;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    SenderCreatePresenter presenter;

    public static Intent getCallingIntent(Context context, int groupId) {
        Intent intent = new Intent(context, SenderCreateActivity.class);
        intent.putExtra(INTENT_EXTRA_GROUP_ID, groupId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender_create);

        inject();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionHandler.REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.permissionAccepted();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PORT_NAMING && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            String[] inputNames = extras.getStringArray(RESULT_EXTRA_INPUT_NAMES);
            String[] outputNames = extras.getStringArray(RESULT_EXTRA_OUTPUT_NAMES);

            if (inputNames == null) {
                inputNames = new String[]{};
            }
            if (outputNames == null) {
                outputNames = new String[]{};
            }
            presenter.portNamesReceived(inputNames, outputNames);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.attach(this);
    }

    private void inject() {
        getGroupComponent()
                .inject(this);
    }

    @OnClick(R.id.bt_create_sender)
    public void onCreateSenderClicked() {
        presenter.createSenderClicked();
    }

    @Override
    public void returnToPreviousView() {
        finish();
    }

    @Override
    public void showGroups(@NonNull List<Group> groups) {
        gl_groups.removeAllViews();
        GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED);
        GridLayout.Spec colSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        for (Group g : groups) {
            CheckBox c = new AppCompatCheckBox(this);
            c.setLayoutParams(new GridLayout.LayoutParams(rowSpec, colSpec));
            c.setText(g.getName());
            c.setTag(g.getId());

            gl_groups.addView(c);
        }
    }

    @Override
    public void selectGroup(@NonNull Group group) {
        for (int i = 0; i < gl_groups.getChildCount(); ++i) {
            if (gl_groups.getChildAt(i).getTag().equals(group.getId())) {
                ((CheckBox) gl_groups.getChildAt(i)).setChecked(true);
            }
        }
    }

    @Override
    public void showPortNamingView(int amountOfInputs, int amountOfOutputs) {
        startActivityForResult(
                PortNamingActivity.getCallingIntent(this, amountOfInputs, amountOfOutputs),
                REQUEST_CODE_PORT_NAMING);
    }

    @NonNull
    @Override
    public String getName() {
        return et_name.getText().toString();
    }

    @NonNull
    @Override
    public String getNumber() {
        return et_number.getText().toString();
    }

    @NonNull
    @Override
    public String getPin() {
        return et_pin.getText().toString();
    }

    @NonNull
    @Override
    public List<Integer> getGroups() {
        List<Integer> groupIds = new ArrayList<>(gl_groups.getChildCount());
        for (int i = 0; i < gl_groups.getChildCount(); ++i) {
            int id = (int) gl_groups.getChildAt(i).getTag();

            groupIds.add(id);
        }
        return groupIds;
    }

    @Override
    public void showErrorName() {
        til_name.setError(getString(R.string.sender_create_error_name));
    }

    @Override
    public void showErrorNumber() {
        til_number.setError(getString(R.string.sender_create_error_number));
    }

    @Override
    public void showErrorPinTooShort() {
        til_pin.setError(getString(R.string.sender_create_error_pin_too_short));
    }

    @Override
    public void showErrorPinNeeded() {
        til_pin.setError(getString(R.string.sender_Create_error_pin_needed));
    }

    @Override
    public void showRationale() {
        PermissionRationaleDialogFragment dia = new PermissionRationaleDialogFragment();
        dia.show(getSupportFragmentManager(), RATIONALE_TAG);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onAccept() {
        presenter.rationaleAccepted();
    }
}
