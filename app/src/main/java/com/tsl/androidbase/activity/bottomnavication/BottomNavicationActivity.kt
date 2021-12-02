package com.tsl.androidbase.activity.bottomnavication

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tsl.androidbase.R
import com.tsl.androidbase.activity.BaseActivity
import com.jaeger.library.StatusBarUtil
import com.tsl.androidbase.repository.db.Db
import kotlinx.android.synthetic.main.activity_bottom_navication.*
import java.util.*

class BottomNavicationActivity : BaseActivity() {
    companion object {
        var TAG = "MainActivity1"
    }


    val data = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bottom_navication)

        lifecycle.addObserver(BottomNavicationPresenter())
        val navController = findNavController(R.id.widget_bna__nav_host_fragment)
        widget_bna_bottom_navigation.setupWithNavController(navController)
        setSupportActionBar(widget_bna_toolbar)
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
