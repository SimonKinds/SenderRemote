package io.kindstrom.senderremote.presentation.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import io.kindstrom.senderremote.R;


public final class PermissionRationaleDialogFragment extends AppCompatDialogFragment {
    private PermissionCallback permissionCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof PermissionCallback) {
            permissionCallback = (PermissionCallback) context;
        } else {
            throw new AssertionError("Container class has to implement the permission callback");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.permission_rationale)
                .setPositiveButton(R.string.accept,
                        (dialogInterface, i) -> permissionCallback.onAccept());
        return builder.create();
    }

    public interface PermissionCallback {
        void onAccept();
    }
}
