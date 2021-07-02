package com.example.androidbase.activity.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.androidbase.R
import com.example.androidbase.activity.BaseActivity
import com.example.androidbase.fragment.first.FirstFragment


class LoginActivity : BaseActivity() {
    companion object{
       var TAG = "JLogin"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d(TAG,"onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onReStart")
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume"+ Navigation.findNavController(this,R.id.id_nav_host_fragment))

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    override fun setStatusBar() {

    }

    override fun onBackPressed() {
        Log.d("Jowney", this.supportFragmentManager.backStackEntryCount.toString())
        super.onBackPressed()
    }
}