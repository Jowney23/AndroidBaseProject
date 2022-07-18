package com.tsl.androidbase.activity.show

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hjq.toast.ToastUtils
import com.jaeger.library.StatusBarUtil
import com.jowney.common.sample.popup.custom.CustomCenterPopup
import com.jowney.common.sample.popup.custom.LoginPopup
import com.jowney.common.util.DensityUtils
import com.jowney.common.util.logger.L
import com.lxj.xpopup.XPopup
import com.tsl.androidbase.R
import com.tsl.androidbase.activity.BaseActivity
import com.tsl.androidbase.widget.BottomShowPopup
import kotlinx.android.synthetic.main.activity_show.*


class ShowActivity : BaseActivity() {
    private lateinit var mBasePopupView: BottomShowPopup
    private lateinit var mShowNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_show)
        var model: ShowActivityViewModel =
            ViewModelProvider(this).get(ShowActivityViewModel::class.java)
        mBasePopupView = BottomShowPopup(this)
        model.setBottomPopup(mBasePopupView)


        XPopup.Builder(this)
            .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
            .enableDrag(true)
            .isViewMode(true)
            .asCustom(mBasePopupView)

        super.onResume()


        widget_sa_constraint.setOnLongClickListener {
            if (mBasePopupView.isDismiss) mBasePopupView.show()
            else mBasePopupView.dismiss()
            false
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY //(修改这个选项，可以设置不同模式)
                    //使用下面三个参数，可以使内容显示在system bar的下面，防止system bar显示或
                    //隐藏时，Activity的大小被resize。
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // 隐藏导航栏和状态栏
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    override fun setStatusBar() {
        StatusBarUtil.setTransparent(this)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {


        when (keyCode) {
            KeyEvent.KEYCODE_ENTER, KeyEvent.KEYCODE_DPAD_CENTER -> L.d("enter--->")
            KeyEvent.KEYCODE_BACK -> {
                L.d("back--->")
            }

            KeyEvent.KEYCODE_DPAD_DOWN ->
                /*    实际开发中有时候会触发两次，所以要判断一下按下时触发 ，松开按键时不触发
                 *    exp:KeyEvent.ACTION_UP
                 */
                if (event!!.action === KeyEvent.ACTION_DOWN) {
                    L.d("down--->")
                }
            KeyEvent.KEYCODE_DPAD_UP -> L.d("up--->")
            KeyEvent.KEYCODE_DPAD_LEFT -> L.d("left--->")
            KeyEvent.KEYCODE_DPAD_RIGHT -> L.d("right--->")
            KeyEvent.KEYCODE_PAGE_DOWN, KeyEvent.KEYCODE_MEDIA_NEXT -> L.d("page down--->")
            KeyEvent.KEYCODE_VOLUME_UP -> L.d("voice up--->")
            KeyEvent.KEYCODE_VOLUME_DOWN -> L.d("voice down--->")
            KeyEvent.KEYCODE_MENU -> {
                if (mBasePopupView.isDismiss) mBasePopupView.show()
                else mBasePopupView.dismiss()
                return true
            }
            else -> {
                L.d("$keyCode")
            }
        }

        return super.onKeyDown(keyCode, event)
    }
}