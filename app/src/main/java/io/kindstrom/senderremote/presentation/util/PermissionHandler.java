package io.kindstrom.senderremote.presentation.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public final class PermissionHandler {
    public static final int REQUEST_CODE = 111;

    public static void requestPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.SEND_SMS},
                REQUEST_CODE);
    }

    public static boolean shouldShowRationale(Activity activity) {
        return shouldShowRationaleReceive(activity) || shouldShowRationaleSend(activity);
    }

    private static boolean shouldShowRationaleReceive(Activity activity) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.RECEIVE_SMS);
    }

    private static boolean shouldShowRationaleSend(Activity activity) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.SEND_SMS);
    }

    public static boolean hasPermissions(Activity activity) {
        return hasReceivePermission(activity) && hasSendPermission(activity);
    }

    private static boolean hasReceivePermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS)
                == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean hasSendPermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED;
    }
}
