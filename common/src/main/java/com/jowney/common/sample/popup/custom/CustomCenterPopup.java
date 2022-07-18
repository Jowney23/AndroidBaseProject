package com.jowney.common.sample.popup.custom;

import android.content.Context;

import androidx.annotation.NonNull;

import com.jowney.common.R;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.util.XPopupUtils;


/**
 * 用例：
 *  Handler().post {
 *             XPopup.Builder(this)
 *                 .hasStatusBarShadow(true)
 *                 .hasShadowBg(true)
 *                 .hasBlurBg(true)
 *                 .dismissOnBackPressed(true)
 *                 .dismissOnTouchOutside(true)
 *                 .borderRadius(20F)
 *                 .isDarkTheme(false)
 *                 .popupWidth(750)
 *                 .popupHeight(500)
 *                 .isDestroyOnDismiss(true)
 *                 .asCustom(CustomCenterPopup(this))
 *                 .show()
 *         }
 * 解释，对于需要直接展示的弹窗，比如在Activity的onResume执行完后就直接展示，需要在Handler().post{}中调用
 * 因为背景模糊或者阴影需要对decorview进行操作，需要其初始化完成
 */
public class CustomCenterPopup extends CenterPopupView {
    public CustomCenterPopup(@NonNull Context context) {
        super(context);
    }

    //如果想要给CenterPopup添加主题颜色，需要重写该方法，并调用applyTheme()
    //不要在自定义布局的parentlayout中添加颜色，这样会遮盖圆角效果
    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        applyTheme();
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_custom_center;
    }

    @Override
    protected int getMaxWidth() {
        return XPopupUtils.getWindowWidth(getContext());
    }
}

