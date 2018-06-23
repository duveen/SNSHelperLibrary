package kr.sharenshare.helper.permission;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;

import kr.sharenshare.helper.R;

public class PermissionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (false) {
            // 오버레이 권한 필요한 경우
        } else {
            // 오버레이 권한 필요 없는 경우
        }

    }

    private boolean requestWindowOverlayPermission() {
        Uri uri = Uri.fromParts("package", packageName, null);
        final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri);

        if(!TextUtils.isEmpty(rationale_message)) {
            new AlertDialog.Builder(this)
                    .setMessage(rationale_message)
                    .setCancelable(false)

                    .setNegativeButton(rationaleConfirmText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivityForResult(intent, REQ_CODE_SYSTEM_ALERT_WINDOW_PERMISSION_REQUEST);
                        }
                    })
                    .show();
            isShownRationaleDialog = true;
        }else {
            startActivityForResult(intent, REQ_CODE_SYSTEM_ALERT_WINDOW_PERMISSION_REQUEST);
        }
    }



}
