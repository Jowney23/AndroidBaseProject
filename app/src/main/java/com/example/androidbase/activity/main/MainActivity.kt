package com.example.androidbase.activity.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidbase.R
import com.example.androidbase.activity.BaseActivity
import com.example.androidbase.viewmodel.WordViewModel
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    companion object {
        var TAG = "MainActivity1"
    }

    private val mWordViewModel by viewModels<WordViewModel>()

    val data = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycle.addObserver(MainPresenter())

        val navController = findNavController(R.id.widget_nav_host_fragment)
        widget_bottom_navigation.setupWithNavController(navController)
        setSupportActionBar(widget_ma_appbar_layout)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun setStatusBar() {
        StatusBarUtil.setColor(this, getColor(R.color.app_colorPrimaryDark), 5);
    }

    fun onTestClickListener(view: View) {

    }
}
