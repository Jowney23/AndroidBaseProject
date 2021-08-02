package com.tsl.androidbase.fragment.show

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jowney.common.util.logger.L
import com.tsl.androidbase.R
import kotlinx.android.synthetic.main.fragment_picture_show.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PictureShowFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mView:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_picture_show, container, false)
        mView.isFocusableInTouchMode = true;
        mView.requestFocus();
        mView.setOnKeyListener { v, keyCode, event ->
            when (keyCode) {
                KeyEvent.KEYCODE_ENTER, KeyEvent.KEYCODE_DPAD_CENTER -> L.d("enter--->")
                KeyEvent.KEYCODE_BACK -> {
                    L.d("back--->")
                }

                KeyEvent.KEYCODE_DPAD_DOWN ->
                    /*    实际开发中有时候会触发两次，所以要判断一下按下时触发 ，松开按键时不触发
                     *    exp:KeyEvent.ACTION_UP
                     */
                    if (event!!.action === KeyEvent.ACTION_DOWN) {

                        L.d("item down--->")
                    }
                KeyEvent.KEYCODE_DPAD_UP -> {
                    L.d("item up--->")
                }
                KeyEvent.KEYCODE_DPAD_LEFT -> {

                    L.d("item left--->")
                }
                KeyEvent.KEYCODE_DPAD_RIGHT -> {
                    L.d("item right--->")
                }
            }
            false
        }
        return mView
    }

    override fun onResume() {
        super.onResume()

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PictureShowFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}