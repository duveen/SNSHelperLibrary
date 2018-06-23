package kr.sharenshare.helper.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.squareup.otto.Subscribe;

import kr.sharenshare.helper.TextUtils;
import kr.sharenshare.helper.bus.PushEvent;

public class PermissionHelper {

    private static final int GRANTED = PackageManager.PERMISSION_GRANTED;
    private static final String PUSH_EVENT_TYPE_REQ_PERMISSION = "push_event_type_req_permission";

    /**
     * Determine whether <em>you</em> have been granted a particular permission.
     *
     * @param permission The name of the permission being checked.
     * @return {@link PackageManager#PERMISSION_GRANTED} if you have the
     * permission, or {@link PackageManager#PERMISSION_DENIED} if not.
     * @Context context Android context
     * @see PackageManager#checkPermission(String, String)
     */
    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == GRANTED;
    }

    /**
     * Determine whether <em>you</em> have been granted a particular permission.
     *
     * @param permissions The name of the permission being checked.
     * @return {@link PackageManager#PERMISSION_GRANTED} if you have the
     * permission, or {@link PackageManager#PERMISSION_DENIED} if not.
     * @Context context Android Context
     * @see PackageManager#checkPermission(String, String)
     */
    public static boolean checkPermissions(Context context, String... permissions) {
        boolean isGranted = true;
        for (String permission : permissions)
            isGranted = isGranted && checkPermission(context, permission);
        return isGranted;
    }

    public static void requestPermissions(Context context, String... permissions) {
        if (permissions == null || permissions.length == 0) return;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra("permissions", permissions);

        context.startActivity(intent);
    }

    public static Intent requestPermissionsIntent(Context context, String... permissions) {
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra("permissions", permissions);
        return intent;
    }

    @Subscribe
    public void pushEvent(PushEvent pushEvent) {
        if (TextUtils.isEmpty(pushEvent.getPushType())) return;

        if (!pushEvent.getPushType().equals(PUSH_EVENT_TYPE_REQ_PERMISSION)) return;

        pushEvent.getPushData("result");
    }

    public interface OnPermissionListener {
        void onGranted(String[] permissions, boolean[] results);

        void onDeny(String[] permissions, boolean[] results);
    }

}
