package com.example.androidbase.activity.main

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MainPresenter:DefaultLifecycleObserver {
    companion object{
       const val TAG = "MainPresenter"
    }
    override fun onCreate(owner: LifecycleOwner) {
     Log.d(TAG,"onCreate   ${owner.lifecycle.currentState.name} "+Thread.currentThread().name)

    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d(TAG,"onResume   ${owner.lifecycle.currentState.name} "+Thread.currentThread().name)
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(TAG,"onPause   ${owner.lifecycle.currentState.name} "+Thread.currentThread().name)
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d(TAG,"onStart   ${owner.lifecycle.currentState.name} "+Thread.currentThread().name)
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(TAG,"onStop   ${owner.lifecycle.currentState.name} "+Thread.currentThread().name)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(TAG,"onDestroy   ${owner.lifecycle.currentState.name} "+Thread.currentThread().name)
    }
}