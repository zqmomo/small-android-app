package com.example.android.forq.comm;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.android.forq.R;

import androidx.annotation.NonNull;

public class ApproveDialog extends Dialog implements View.OnClickListener{

    private String titleStr;
    private String contentStr;
    private String cancelStr;
    private String goStr;

    public interface ApproveDialogHelper {
        void go();

        void cancel();
    }

    private ApproveDialogHelper mConfirmDialogHelper;

    public ApproveDialog(@NonNull Context context ,String titleStr, String contentStr, String goStr, String ts_cancle_auth, ApproveDialogHelper helper) {
        super(context, R.style.CustomDialog);
        this.titleStr = titleStr;
        this.contentStr = contentStr;
        this.goStr = goStr;
        this.cancelStr = ts_cancle_auth;
        this.mConfirmDialogHelper = helper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scgz_custondialog);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();//获得窗口
        assert window != null;
        WindowManager manager = window.getWindowManager();//获得管理器
        Display display = manager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (dm.widthPixels*0.8);
        window.setAttributes(attributes);
        init();
    }

    private void init(){
        TextView tv_title = findViewById(R.id.auth_dialog_title);
        TextView tv_content = findViewById(R.id.auth_dialog_content);
        TextView tv_go = findViewById(R.id.ts_auth);
        TextView tv_cancle = findViewById(R.id.ts_cancle_ayth);

        tv_title.setText(titleStr);
        tv_content.setText(contentStr);
        tv_go.setText(goStr);
        tv_cancle.setText(cancelStr);

        tv_go.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ts_auth:
                //确定
                if(mConfirmDialogHelper != null){
                    mConfirmDialogHelper.go();
                }
                dismiss();
                break;
            case R.id.ts_cancle_ayth:
                //取消
                if (mConfirmDialogHelper != null)
                    mConfirmDialogHelper.cancel();
                dismiss();
                break;
            default:
                break;
        }


    }
}
