package io.kindstrom.senderremote.presentation.view;

import android.app.Activity;

public interface PermissionView {
    void showRationale();

    // needed to be able to request the permissions without having the view do all the work
    Activity getActivity();
}
