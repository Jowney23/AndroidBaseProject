package com.jowney.common.sample.popup.custom;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.hjq.toast.ToastUtils;
import com.jowney.common.R;
import com.lxj.xpopup.core.HorizontalAttachPopupView;


/**
 * Description: 自定义Attach弹窗，水平方向的
 * Create by lxj, at 2019/3/13
 */
public class CustomAttachPopup extends HorizontalAttachPopupView {
    public CustomAttachPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_zan).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("赞");
                dismiss();
            }
        });
        findViewById(R.id.tv_comment).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("评论");
                dismiss();
            }
        });
    }

}
