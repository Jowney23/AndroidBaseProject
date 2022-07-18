    package com.tsl.androidbase.fragment.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jowney.common.widget.banner.Banner
import com.jowney.common.widget.banner.transformer.AlphaPageTransformer
import com.tsl.androidbase.R
import com.tsl.androidbase.activity.show.ShowActivityViewModel
import com.tsl.androidbase.adapter.ImageBannerAdapter
import com.tsl.androidbase.bean.DataBean
import kotlinx.android.synthetic.main.fragment_p_p_t_show.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PPTShowFragment : Fragment() {
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
        var view = inflater.inflate(R.layout.fragment_p_p_t_show, container, false)
        var model: ShowActivityViewModel = ViewModelProvider(requireActivity()).get(ShowActivityViewModel::class.java)
        val nav: NavController? = requireActivity().findNavController(R.id.widget_sa_nav_host_fragment)

        //(可以和其他PageTransformer组合使用，比如AlphaPageTransformer，注意但和其他带有缩放的PageTransformer会显示冲突)
        //添加透明效果(画廊配合透明效果更棒)

        var banner: Banner<DataBean, ImageBannerAdapter> = view.widget_pptsf_banner as Banner<DataBean, ImageBannerAdapter>
        banner.addPageTransformer(AlphaPageTransformer());
        banner.setAdapter(ImageBannerAdapter(DataBean.testData))
        view.widget_pptsf_banner.setIndicator(view.widget_pptsf_indicator, false)

        view.widget_pptsf_banner.setBannerGalleryMZ(20)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
               override fun handleOnBackPressed() {
                    model.getBottomPopup()?.show()
                   nav?.popBackStack()
               }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        // The callback can be enabled or disabled here or in handleOnBackPressed()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PPTShowFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}