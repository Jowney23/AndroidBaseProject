package com.example.androidbase.net.api

import com.example.androidbase.APP
import com.example.androidbase.R

object ServerURL {
        var BASE_URL: String? = APP.mContext?.getString(R.string.base_url);
}