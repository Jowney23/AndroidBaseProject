package com.example.androidbase.fragment.first

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.androidbase.R
import com.example.androidbase.fragment.third.ThirdFragment
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {

    companion object {
        var TAG = "JFragment"
        fun newInstance() =
            FirstFragment()
    }

    private lateinit var viewModel: FirstViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(FirstViewModel::class.java)
        Log.d(TAG, "First onActivityCreated")
        id_first_ft_bt1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_firstFragment_to_secondFragment)
        }
        id_first_ft_bt2.setOnClickListener {

        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "First  onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "First  onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "First onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "First onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "First onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "First onDestroy")
    }
    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "First onDestroy")
    }

}
