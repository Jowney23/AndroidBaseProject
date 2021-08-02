package com.tsl.androidbase.fragment.third

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.tsl.androidbase.R
import com.tsl.androidbase.fragment.first.FirstFragment

class ThirdFragment : Fragment() {

    companion object {
        fun newInstance() =
            ThirdFragment()
    }

    private lateinit var viewModel: ThirdViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(FirstFragment.TAG, "Third onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(FirstFragment.TAG, "Third onCreate")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThirdViewModel::class.java)
        Log.d(FirstFragment.TAG, "Third onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(FirstFragment.TAG, "Third onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(FirstFragment.TAG, "Third onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(FirstFragment.TAG, "Third onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(FirstFragment.TAG, "Third onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(FirstFragment.TAG, "Third onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(FirstFragment.TAG, "Third onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(FirstFragment.TAG, "Third onDetach")
    }
}
