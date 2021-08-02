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
import com.jowney.player.player.AndroidMediaPlayer
import com.jowney.player.player.VideoView
import com.jowney.player.ui.StandardVideoController
import com.tsl.androidbase.R
import com.tsl.androidbase.activity.show.ShowActivityViewModel
import kotlinx.android.synthetic.main.fragment_video_show.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VideoShowFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mView: View
    private lateinit var mVideoView: VideoView<AndroidMediaPlayer>
    val urls: Array<String> = arrayOf("file:///mnt/sdcard/TSLMedia/1.mp4","file:///mnt/sdcard/TSLMedia/2.mp4",
        "file:///mnt/sdcard/TSLMedia/3.mp4","file:///mnt/sdcard/TSLMedia/4.mp4",
        "file:///mnt/sdcard/TSLMedia/5.mp4")
    lateinit var mStandardVideoController :StandardVideoController
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
        mView = inflater.inflate(R.layout.fragment_video_show, container, false)
        val nav: NavController =
            requireActivity().findNavController(R.id.widget_sa_nav_host_fragment)
        val model = ViewModelProvider(requireActivity()).get(ShowActivityViewModel::class.java)
        mVideoView = mView.widget_vsf_videoview as VideoView<AndroidMediaPlayer>
        mStandardVideoController = StandardVideoController(requireContext())
        mStandardVideoController.addDefaultControlComponent("投名状", false)

        mVideoView.setVideoController(mStandardVideoController)
        val url = "file:///mnt/sdcard/TSLMedia/3.mp4"

        mVideoView.setUrl(url)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    model.getBottomPopup()?.show()
                    nav?.popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return mView
    }

    override fun onResume() {
        super.onResume()
        //监听播放结束

        //监听播放结束
        mVideoView.addOnStateChangeListener(object : VideoView.SimpleOnStateChangeListener() {
            private var mCurrentVideoPosition = 0
            override fun onPlayStateChanged(playState: Int) {
                if (playState == VideoView.STATE_PLAYBACK_COMPLETED) {
                    if (mCurrentVideoPosition >= urls.size)  mCurrentVideoPosition =0
                    mVideoView.release()
                    //重新设置数据
                    mVideoView.setUrl(urls[mCurrentVideoPosition])
                    mVideoView.setVideoController(mStandardVideoController)
                    //开始播放
                    mVideoView.start()
                    mCurrentVideoPosition++
                }
            }
        })

        mVideoView.start()
    }
    override fun onDestroy() {
        super.onDestroy()
        mVideoView.release()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoShowFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}