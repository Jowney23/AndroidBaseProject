package com.tsl.androidbase.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.tsl.androidbase.R
import com.jowney.common.util.logger.L
import com.tsl.androidbase.repository.db.Db
import com.tsl.androidbase.repository.db.tables.Word
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.*
import java.util.concurrent.Executors

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    val scope = MainScope()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        root.widget_fm_home_bt.setOnClickListener {
            scope.launch(){
                L.v("wao 协程开始")
               var data = async { getResult(3) }
                L.v("计算的结果是${data.await()}")
                L.v("会不会等上面得await")
            }
            L.v("wao 协程下面")
            //  ServerOperator.operatorDataJoke()
            //  CrashReport.testJavaCrash()
            //   CrashReport.testNativeCrash()
            /*  L.t("###")
              L.d("12       ,3\n ddddddddddddddddd")
              L.d(arrayListOf<Int>(1, 2, 3, 4))
              L.d("这是啥？%d", 1)*/
            //ZipTool.unzipFile(File("mnt/sdcard/TSLppt/ppt_2.zip"),File("mnt/sdcard/TSLppt/"))

        }
        return root
    }

    private suspend fun getResult(num: Int): Int {
        delay(50000)
        L.v("计算完结果")
        return num * num
    }
}
