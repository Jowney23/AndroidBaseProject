package com.tsl.androidbase.activity.bottomnavication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jaeger.library.StatusBarUtil
import com.jowney.common.accessibility.CommonAccessibilityService
import com.jowney.common.util.DensityUtils
import com.jowney.common.util.logger.L
import com.tsl.androidbase.R
import com.tsl.androidbase.activity.BaseActivity
import com.tsl.androidbase.repository.db.Db
import kotlinx.android.synthetic.main.activity_bottom_navication.*
import java.util.*
import java.util.concurrent.Executors

class BottomNavicationActivity : BaseActivity() {
    companion object {
        var TAG = "MainActivity1"
    }


    val data = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DensityUtils.setDensity(application,this)
        setContentView(R.layout.activity_bottom_navication)

        lifecycle.addObserver(BottomNavicationPresenter())
        val navController = findNavController(R.id.widget_bna__nav_host_fragment)
        widget_bna_bottom_navigation.setupWithNavController(navController)
        setSupportActionBar(widget_bna_toolbar)

        Executors.newSingleThreadExecutor().submit {
            val dd = Db.getDb(this).isOpen
            L.v(dd.toString())
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this,
            CommonAccessibilityService::class.java)
        startService(intent);
    }

    override fun setStatusBar() {
        StatusBarUtil.setColor(this, getColor(R.color.app_colorPrimaryDark), 5);
    }

    fun onTestClickListener(view: View) {

    }
}
