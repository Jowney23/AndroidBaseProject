package com.example.androidbase.activity.login

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidbase.R
import com.example.androidbase.activity.main.MainPresenter

class LoginActivity : AppCompatActivity() {
    companion object{
       var TAG = "LoginActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
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
}