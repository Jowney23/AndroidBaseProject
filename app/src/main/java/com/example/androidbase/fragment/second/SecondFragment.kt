package com.example.androidbase.fragment.second

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.androidbase.R
import com.example.androidbase.fragment.first.FirstFragment
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() =
            SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)
        // TODO: Use the ViewModel
        id_second_ft_bt1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_secondFragment_to_thirdFragment)
        }
        id_second_ft_bt2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_secondFragment_to_firstFragment  )
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d(FirstFragment.TAG, "Second onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(FirstFragment.TAG, "Second onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d(FirstFragment.TAG, "Second onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(FirstFragment.TAG, "Second onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(FirstFragment.TAG, "Second onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(FirstFragment.TAG, "Second onDestroy")
    }
}
