package com.jowney.common.sample.popup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.hjq.toast.ToastUtils;
import com.jowney.common.R;
import com.jowney.common.sample.popup.custom.CustomAttachPopup;
import com.jowney.common.sample.popup.custom.CustomAttachPopup2;
import com.jowney.common.sample.popup.custom.CustomDrawerPopupView;
import com.jowney.common.sample.popup.custom.CustomEditTextBottomPopup;
import com.jowney.common.sample.popup.custom.CustomFullScreenPopup;
import com.jowney.common.sample.popup.custom.ListDrawerPopupView;
import com.jowney.common.sample.popup.custom.PagerBottomPopup;
import com.jowney.common.sample.popup.custom.PagerDrawerPopup;
import com.jowney.common.sample.popup.custom.QQMsgPopup;
import com.jowney.common.sample.popup.custom.ZhihuCommentPopup;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.lxj.xpopup.util.XPermission;

//常用弹出窗口样式选择
public class CommonPopupActivity extends AppCompatActivity implements View.OnClickListener {
    CustomDrawerPopupView drawerPopupView;
    AttachPopupView attachPopupView;
    BasePopupView popupView;
    LoadingPopupView loadingPopup;
    CustomAttachPopup2 customAttach2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity_popup);
        findViewById(R.id.btnShowConfirm).setOnClickListener(this);
        findViewById(R.id.btnBindLayout).setOnClickListener(this);
        findViewById(R.id.btnShowPosition1).setOnClickListener(this);
        findViewById(R.id.btnShowPosition2).setOnClickListener(this);
        findViewById(R.id.btnShowInputConfirm).setOnClickListener(this);
        findViewById(R.id.btnShowCenterList).setOnClickListener(this);
        findViewById(R.id.btnShowCenterListWithCheck).setOnClickListener(this);
        findViewById(R.id.btnShowLoading).setOnClickListener(this);
        findViewById(R.id.btnShowBottomList).setOnClickListener(this);
        findViewById(R.id.btnShowBottomListWithCheck).setOnClickListener(this);
        findViewById(R.id.btnShowDrawerLeft).setOnClickListener(this);
        findViewById(R.id.btnShowDrawerRight).setOnClickListener(this);
        findViewById(R.id.btnCustomBottomPopup).setOnClickListener(this);
        findViewById(R.id.btnPagerBottomPopup).setOnClickListener(this);
        findViewById(R.id.btnCustomEditPopup).setOnClickListener(this);
        findViewById(R.id.btnFullScreenPopup).setOnClickListener(this);
        findViewById(R.id.btnAttachPopup1).setOnClickListener(this);
        findViewById(R.id.btnAttachPopup2).setOnClickListener(this);
        findViewById(R.id.tv1).setOnClickListener(this);
        findViewById(R.id.tv2).setOnClickListener(this);
        findViewById(R.id.tv3).setOnClickListener(this);
        findViewById(R.id.btnMultiPopup).setOnClickListener(this);
        findViewById(R.id.btnShowInBackground).setOnClickListener(this);

        // 必须在事件发生前，调用这个方法来监视View的触摸
        final XPopup.Builder builder = new XPopup.Builder(this)
                .watchView(findViewById(R.id.btnShowAttachPoint));
        findViewById(R.id.btnShowAttachPoint).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                XPopup.fixLongClick(v);//能保证弹窗弹出后，下层的View无法滑动
                builder.asAttachList(new String[]{"置顶11", "复制", "删除", "编辑编辑编辑编辑"
                        }, null,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                ToastUtils.show("click " + text);
                            }
                        })
                        .show();
                return true;
            }
        });

        drawerPopupView = new CustomDrawerPopupView(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();//依附于某个View的Attach类型弹窗
        if (id == R.id.btnShowConfirm) { //带确认和取消按钮的弹窗
            /*if(popupView==null)*/
            popupView = new XPopup.Builder(this)
                    .dismissOnBackPressed(true)
                    .dismissOnTouchOutside(true)
//                        .hasNavigationBar(false)
                    .isDestroyOnDismiss(true)
//                        .navigationBarColor(Color.BLUE)
//                        .hasBlurBg(true)
//                         .dismissOnTouchOutside(false)
//                         .autoDismiss(false)
//                        .popupAnimation(PopupAnimation.NoAnimation)
//                        .isLightStatusBar(true)
//                        .setPopupCallback(new DemoXPopupListener())
                    .asConfirm("哈哈", "床前明月光，疑是地上霜；举头望明月，低头思故乡。",
                            "取消", "确定",
                            new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                    ToastUtils.show("click confirm");
                                }
                            }, null, false);
            popupView.show();
        } else if (id == R.id.btnBindLayout) {  //复用项目中已有布局，使用XPopup已有的交互能力
            new XPopup.Builder(this)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asConfirm("复用项目已有布局", "您可以复用项目已有布局，来使用XPopup强大的交互能力和逻辑封装，弹窗的布局完全由你自己控制。\n" +
                                    "注意：你自己的布局必须提供一些控件Id，否则XPopup找不到View。\n具体需要提供哪些Id，请查看文档[内置弹窗]一章。",
                            "关闭", "XPopup牛逼",
                            new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                    ToastUtils.show("click ");
                                }
                            }, null, false, R.layout.my_confim_popup) //最后一个参数绑定已有布局
                    .show();
        } else if (id == R.id.btnShowInputConfirm) { //带确认和取消按钮，输入框的弹窗
            new XPopup.Builder(this)
                    .hasStatusBarShadow(false)
                    //.dismissOnBackPressed(false)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .autoOpenSoftInput(true)
                    .isDarkTheme(true)
                    .setPopupCallback(new DemoXPopupListener())
//                        .autoFocusEditText(false) //是否让弹窗内的EditText自动获取焦点，默认是true
                    //.moveUpToKeyboard(false)   //是否移动到软键盘上面，默认为true
                    .asInputConfirm("我是标题", null, null, "我是默认Hint文字",
                            new OnInputConfirmListener() {
                                @Override
                                public void onConfirm(String text) {
//                                new XPopup.Builder(getContext()).asLoading().show();
                                }
                            })
                    .show();
        } else if (id == R.id.btnShowCenterList) { //在中间弹出的List列表弹窗
            new XPopup.Builder(this)
//                        .maxWidth(600)
                    .isDarkTheme(true)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asCenterList("请选择一项", new String[]{"条目1", "条目2", "条目3", "条目4"},
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    ToastUtils.show("click " + text);
                                }
                            })
//                        .bindLayout(R.layout.my_custom_attach_popup) //自定义布局
                    .show();
        } else if (id == R.id.btnShowCenterListWithCheck) { //在中间弹出的List列表弹窗，带选中效果
            new XPopup.Builder(this)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asCenterList("请选择一项", new String[]{"条目1", "条目2", "条目3", "条目4"},
                            null, 1,
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    ToastUtils.show("click " + text);
                                }
                            })
                    .show();
        } else if (id == R.id.btnShowLoading) { //在中间弹出的Loading加载框
            if (loadingPopup == null) {
                loadingPopup = (LoadingPopupView) new XPopup.Builder(this)
                        .dismissOnBackPressed(false)
                        .asLoading("加载中")
                        .show();
            } else {
                loadingPopup.show();
            }
            loadingPopup.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingPopup.setTitle("加载中长度变化啊");
                    loadingPopup.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingPopup.setTitle("");
                        }
                    }, 2000);
                }
            }, 2000);
//                loadingPopup.smartDismiss();
//                loadingPopup.dismiss();
            loadingPopup.delayDismissWith(6000, new Runnable() {
                @Override
                public void run() {
                    ToastUtils.show("click ");
                }
            });
        } else if (id == R.id.btnShowBottomList) { //从底部弹出，带手势拖拽的列表弹窗
            new XPopup.Builder(this)
                    .isDarkTheme(true)
                    .hasShadowBg(true)
//                            .hasBlurBg(true)
//                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asBottomList("请选择一项", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5"},
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    ToastUtils.show("click " + text);
                                }
                            }).show();
        } else if (id == R.id.btnShowBottomListWithCheck) { //从底部弹出，带手势拖拽的列表弹窗,带选中效果
            new XPopup.Builder(this)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asBottomList("标题可以没有", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5"},
                            null, 2,
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    ToastUtils.show("click " + text);
                                }
                            })
                    .show();
        } else if (id == R.id.btnCustomBottomPopup) { //自定义的底部弹窗
            new XPopup.Builder(this)
                    .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                    .enableDrag(true)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
//                        .isThreeDrag(true) //是否开启三阶拖拽，如果设置enableDrag(false)则无效
                    .asCustom(new ZhihuCommentPopup(this)/*.enableDrag(false)*/)
                    .show();
        } else if (id == R.id.btnPagerBottomPopup) { //自定义的底部弹窗
            new XPopup.Builder(this)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                    .asCustom(new PagerBottomPopup(this))
                    .show();
        } else if (id == R.id.tv1 || id == R.id.tv2 || id == R.id.tv3) {
            attachPopupView = new XPopup.Builder(this)
                    .hasShadowBg(false)
                    .isClickThrough(true)
//                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
//                        .isDarkTheme(true)
//                        .popupAnimation(PopupAnimation.ScrollAlphaFromTop) //NoAnimation表示禁用动画
//                        .isCenterHorizontal(true) //是否与目标水平居中对齐
//                        .offsetY(60)
//                        .offsetX(80)
//                        .popupPosition(PopupPosition.Top) //手动指定弹窗的位置
//                        .popupWidth(500)
                    .atView(v)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
                    .asAttachList(new String[]{"分享", "编辑", "不带icon", "分享",
//                                        "分享", "编辑", "不带icon", "分享",
//                                        "分享", "编辑", "不带icon", "分享",
//                                        "分享", "编辑", "不带icon", "分享"
                            },
                            null,
//                                new int[]{R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round},
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    ToastUtils.show("click " + text);
                                }
                            }, 0, 0/*, Gravity.LEFT*/);
            ;
            attachPopupView.show();
        } else if (id == R.id.btnAttachPopup1) { //水平方向的Attach弹窗，就像微信朋友圈的点赞弹窗那样
            new XPopup.Builder(this)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
//                        .offsetX(50) //偏移10
//                        .offsetY(10)  //往下偏移10
//                        .popupPosition(PopupPosition.Right) //手动指定位置，有可能被遮盖
                    .hasShadowBg(false) // 去掉半透明背景
                    .atView(v)
                    .asCustom(new CustomAttachPopup(this))
                    .show();
        } else if (id == R.id.btnAttachPopup2) {
            if (customAttach2 == null) customAttach2 = new CustomAttachPopup2(this);
            new XPopup.Builder(this)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .atView(v)
                    .isViewMode(true)
                    .hasShadowBg(false) // 去掉半透明背景
                    .asCustom(customAttach2)
                    .show();
        } else if (id == R.id.btnShowDrawerLeft) { //像DrawerLayout一样的Drawer弹窗
            new XPopup.Builder(this)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
//                        .asCustom(new CustomDrawerPopupView(getContext()))
//                        .hasShadowBg(false)
                    .asCustom(new PagerDrawerPopup(this))
//                        .asCustom(new ListDrawerPopupView(getContext()))
                    .show();
        } else if (id == R.id.btnShowDrawerRight) {
            new XPopup.Builder(this)
                    .dismissOnTouchOutside(false)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .popupPosition(PopupPosition.Right)//右边
                    .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(new ListDrawerPopupView(this))
                    .show();
        } else if (id == R.id.btnFullScreenPopup) { //全屏弹窗，看起来像Activity
            new XPopup.Builder(this)
                    .hasStatusBarShadow(true)
//                        .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .autoOpenSoftInput(true)
                    .asCustom(new CustomFullScreenPopup(this))
                    .show();
        } else if (id == R.id.btnCustomEditPopup) { //自定义依附在输入法之上的Bottom弹窗
            new XPopup.Builder(this)
                    .autoOpenSoftInput(true)
                    //.isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asCustom(new CustomEditTextBottomPopup(this))
                    .show();
        } else if (id == R.id.btnShowPosition1) {
            new XPopup.Builder(this)
//                        .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .offsetY(300)
                    .offsetX(-100)
                    .popupAnimation(PopupAnimation.TranslateFromLeft)
                    .asCustom(new QQMsgPopup(this))
                    .show();
        } else if (id == R.id.btnShowPosition2) {
            new XPopup.Builder(this)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .isCenterHorizontal(true)
                    .offsetY(200)
                    .asCustom(new QQMsgPopup(this))
                    .show();
        } else if (id == R.id.btnMultiPopup) {
           // startActivity(new Intent(this, DemoActivity.class));
        } else if (id == R.id.btnShowInBackground) {//申请悬浮窗权限
            XPopup.requestOverlayPermission(this, new XPermission.SimpleCallback() {
                @Override
                public void onGranted() {
                    ToastUtils.show("等待2秒后弹出XPopup！！！");
                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory(Intent.CATEGORY_HOME);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(homeIntent);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new XPopup.Builder(CommonPopupActivity.this)
                                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                                    .enableShowWhenAppBackground(true)  //运行在应用后台弹出
                                    .asConfirm("XPopup牛逼", "XPopup支持直接在后台弹出！", new OnConfirmListener() {
                                        @Override
                                        public void onConfirm() {
                                           // startActivity(new Intent(this, CommonPopupActivity.class));
                                        }
                                    }).show();
                        }
                    }, 2000);
                }

                @Override
                public void onDenied() {
                    ToastUtils.show("权限拒绝需要申请悬浮窗权限！");
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    static class DemoXPopupListener extends SimpleCallback {
        @Override
        public void onCreated(BasePopupView pv) {
            Log.e("tag", "onCreated");
        }

        @Override
        public void onShow(BasePopupView popupView) {
            Log.e("tag", "onShow");
        }

        @Override
        public void onDismiss(BasePopupView popupView) {
            Log.e("tag", "onDismiss");
        }

        @Override
        public void beforeDismiss(BasePopupView popupView) {
            Log.e("tag", "beforeDismiss");
        }

        //如果你自己想拦截返回按键事件，则重写这个方法，返回true即可
        @Override
        public boolean onBackPressed(BasePopupView popupView) {
            Log.e("tag", "拦截的返回按键，按返回键XPopup不会关闭了");
            ToastUtils.show("onBackPressed返回true，拦截了返回按键，按返回键XPopup不会关闭了");
            return true;
        }

        @Override
        public void onKeyBoardStateChanged(BasePopupView popupView, int height) {
            super.onKeyBoardStateChanged(popupView, height);
            Log.e("tag", "onKeyBoardStateChanged height: " + height);
        }
    }
}
