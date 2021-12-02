package com.tsl.androidbase.fragment.first

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.jowney.common.util.logger.L
import com.tsl.androidbase.R
import kotlinx.android.synthetic.main.fragment_first.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executors


class FirstFragment : Fragment() {

    companion object {
        var TAG = "JFragment"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_first, container, false)
        view.findViewById<Button>(R.id.id_first_ft_bt1).setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_firstFragment_to_secondFragment)
        }
        view.findViewById<Button>(R.id.id_first_ft_bt2).setOnClickListener {
            EventBus.getDefault().post("123")
        }
        return view
    }

    override fun onResume() {
        super.onResume()
       Executors.newSingleThreadExecutor().submit{
           L.v("123 开始发送")
           EventBus.getDefault().post("123")
           EventBus.getDefault().post("123")
           EventBus.getDefault().post("123")
           EventBus.getDefault().post("123")
           EventBus.getDefault().post("123")
           EventBus.getDefault().post("123")
           L.v("123 发送完了")
       }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun test(i: String) {
        Thread.sleep(3000)
        L.v(i + "执行")
    }

}
