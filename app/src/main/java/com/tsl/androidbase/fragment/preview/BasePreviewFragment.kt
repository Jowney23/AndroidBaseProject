package com.tsl.androidbase.fragment.preview

import androidx.fragment.app.Fragment

abstract class BasePreviewFragment : Fragment() {
    abstract fun onDirectionalPadLeft(): Boolean
    abstract fun onDirectionalPadRight(): Boolean
    abstract fun onDirectionalPadEnter(): Boolean
}