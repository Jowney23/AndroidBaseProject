package com.jowney.common.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.jowney.common.R;

import java.util.Random;

//activity动画切换、statusBar颜色透明度调节
//ToolBar使用
public class CommonAnimationActivity extends AppCompatActivity {
    private TextView textView;
    private SeekBar seekBar;
    private Toolbar mToolbar;
    private EditText mEditText;
    private int mColor = 0;
    private int mAlpha = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.common_activity_animation);

        textView = findViewById(R.id.id_tv);
        seekBar = findViewById(R.id.id_sb_change_alpha);
        mToolbar = findViewById(R.id.id_tbar);
        mEditText = findViewById(R.id.id_common_toolbar_et);
        //toolbar常用设置
        //   mToolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        // mToolbar.setTitle("123");
        //  mToolbar.setSubtitle("我是SubTitle");
        //   mToolbar.setLogo(R.drawable.ic_favorite_24dp);
        //用toolbar代替actionbar

        mEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (mEditText.getRight() - mEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        ToastUtils.show("11111111");
                        return true;
                    }
                }
                return false;

            }
        });

        setSupportActionBar(mToolbar);
        seekBar.setMax(255);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                StatusBarUtil.setColor(CommonAnimationActivity.this, mColor, mAlpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusBarUtil.setColor(this, 0xff3948B6, 10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void onTestClickListener(View view) {
        Random random = new Random();
        mColor = -0x1000000 | random.nextInt(0xffffff);
        mToolbar.setBackgroundColor(mColor);
        StatusBarUtil.setColor(this, mColor, mAlpha);
    }
}
