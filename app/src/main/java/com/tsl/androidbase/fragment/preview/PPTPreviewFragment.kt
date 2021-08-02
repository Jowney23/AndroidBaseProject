package com.tsl.androidbase.fragment.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxj.easyadapter.EasyAdapter
import com.lxj.easyadapter.MultiItemTypeAdapter
import com.lxj.easyadapter.ViewHolder
import com.tsl.androidbase.R
import com.tsl.androidbase.activity.show.ShowActivityViewModel
import kotlinx.android.synthetic.main.fragment_p_p_t_preview.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PPTPreviewFragment : BasePreviewFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_p_p_t_preview, container, false)
        val nav: NavController? = requireActivity().findNavController(R.id.widget_sa_nav_host_fragment)
        val model:ShowActivityViewModel = ViewModelProvider(requireActivity()).get(ShowActivityViewModel::class.java)
        val rv = view.widget_pptpf_rv
        val list: MutableList<Int> = ArrayList()
        list.add(R.mipmap.image1)
        list.add(R.mipmap.image2)
        list.add(R.mipmap.image3)
        list.add(R.mipmap.image4)
        list.add(R.mipmap.image5)

        rv.adapter = object : EasyAdapter<Int>(list, R.layout.item_picture_preview) {
            override fun bind(holder: ViewHolder, resourceId: Int, position: Int) {
                with(holder) {
                    setImageResource(R.id.widget_pptpf_image,resourceId)
                }
            }
        }.apply {
            setOnItemClickListener(object : MultiItemTypeAdapter.SimpleOnItemClickListener() {
                override fun onItemClick(
                    view: View,
                    holder: RecyclerView.ViewHolder,
                    position: Int
                ) {
                    super.onItemClick(view, holder, position)
                    model.getBottomPopup()?.dismiss()
                    nav?.navigate(R.id.action_pictureShowFragment_to_PPTShowFragment)
                }
            })
        }
        rv.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        return view
    }

    override fun onDirectionalPadLeft() :Boolean{
        return true
    }

    override fun onDirectionalPadRight():Boolean {
        return true
    }

    override fun onDirectionalPadEnter():Boolean {
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PPTPreviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}