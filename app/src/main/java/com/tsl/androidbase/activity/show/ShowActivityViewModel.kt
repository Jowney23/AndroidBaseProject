package com.tsl.androidbase.activity.show

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsl.androidbase.widget.BottomShowPopup

class ShowActivityViewModel : ViewModel() {
    private var mBottomShowPopup: MutableLiveData<BottomShowPopup> = MutableLiveData<BottomShowPopup>()
    fun setBottomPopup(bottomShowPopup: BottomShowPopup) {
        mBottomShowPopup.value = bottomShowPopup
    }

    fun getBottomPopup(): BottomShowPopup? {
    return mBottomShowPopup.value
    }
}