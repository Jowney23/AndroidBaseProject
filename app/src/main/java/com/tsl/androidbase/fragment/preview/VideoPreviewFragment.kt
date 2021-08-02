package com.tsl.androidbase.fragment.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jowney.common.util.logger.L
import com.lxj.easyadapter.EasyAdapter
import com.lxj.easyadapter.MultiItemTypeAdapter
import com.lxj.easyadapter.ViewHolder
import com.tsl.androidbase.R
import com.tsl.androidbase.activity.show.ShowActivityViewModel
import kotlinx.android.synthetic.main.fragment_video_preview.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class VideoPreviewFragment : BasePreviewFragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mView: View
    private var mCurrentIndex: Int = 0
    private val mRvManager: LinearLayoutManager =
        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    private lateinit var mRv: RecyclerView
    private val mPreviewResourceList: MutableList<Int> = ArrayList()
    private val mViewHolderList: MutableList<ViewHolder> = ArrayList()
    private var mResourceCounts: Int = 0
    private var mNav: NavController? = null
    private lateinit var mModel: ShowActivityViewModel

    override

    fun onCreate(savedInstanceState: Bundle?) {
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
        mView = inflater.inflate(R.layout.fragment_video_preview, container, false)
        //导航activity中的 PPT Fragment、Picture Fragment、Video Fragment
        mNav =
            requireActivity().findNavController(R.id.widget_sa_nav_host_fragment)
        mModel =
            ViewModelProvider(requireActivity()).get(ShowActivityViewModel::class.java)

        mRv = mView.widget_vpf_rv

        mPreviewResourceList.add(R.mipmap.image1)
        mPreviewResourceList.add(R.mipmap.image2)
        mPreviewResourceList.add(R.mipmap.image3)
        mPreviewResourceList.add(R.mipmap.image4)
        mPreviewResourceList.add(R.mipmap.image5)
        mPreviewResourceList.add(R.mipmap.image1)
        mPreviewResourceList.add(R.mipmap.image2)
        mPreviewResourceList.add(R.mipmap.image3)
        mPreviewResourceList.add(R.mipmap.image4)
        mPreviewResourceList.add(R.mipmap.image5)
        mResourceCounts = mPreviewResourceList.size
        mRv.adapter =
            object : EasyAdapter<Int>(mPreviewResourceList, R.layout.item_picture_preview) {
                override fun bind(holder: ViewHolder, resourceId: Int, position: Int) {
                    with(holder) {
                        setImageResource(R.id.widget_pptpf_image, resourceId)
                    }
                    mViewHolderList.add(holder)
                    L.d("bind $position")
                }
            }.apply {
                setOnItemClickListener(object : MultiItemTypeAdapter.SimpleOnItemClickListener() {
                    override fun onItemClick(
                        view: View,
                        holder: RecyclerView.ViewHolder,
                        position: Int
                    ) {
                        super.onItemClick(view, holder, position)
                        mNav?.navigate(R.id.action_pictureShowFragment_to_videoShowFragment)
                        mModel.getBottomPopup()?.dismiss()
                    }
                })
            }
        mRv.layoutManager = mRvManager
        return mView
    }

    override fun onDirectionalPadLeft(): Boolean {
        mCurrentIndex--
        if (mCurrentIndex < 0) {
            mCurrentIndex = 0
        }
        mRvManager.smoothScrollToPosition(mRv, null, mCurrentIndex)
        mViewHolderList[mCurrentIndex].getView<ConstraintLayout>(R.id.widget_pptpf_ll)
            .setBackgroundResource(R.drawable.preview_item_bg_focus)
        mViewHolderList[mCurrentIndex + 1].getView<ConstraintLayout>(R.id.widget_pptpf_ll)
            .setBackgroundResource(R.drawable.preview_item_bg_dissfocus)
        L.d("videopreviewfragment left")
        return true
    }

    override fun onDirectionalPadRight(): Boolean {
        mCurrentIndex++
        if (mCurrentIndex >= mResourceCounts) {
            mCurrentIndex = mResourceCounts - 1
        }
        mRvManager.smoothScrollToPosition(mRv, null, mCurrentIndex)

        mViewHolderList[mCurrentIndex].getView<ConstraintLayout>(R.id.widget_pptpf_ll)
            .setBackgroundResource(R.drawable.preview_item_bg_focus)
        mViewHolderList[mCurrentIndex - 1].getView<ConstraintLayout>(R.id.widget_pptpf_ll)
            .setBackgroundResource(R.drawable.preview_item_bg_dissfocus)
        L.d("videopreviewfragment right")
        return true
    }

    override fun onDirectionalPadEnter(): Boolean {

        mModel.getBottomPopup()?.dismiss()
        mNav?.navigate(R.id.action_pictureShowFragment_to_videoShowFragment)
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoPreviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}