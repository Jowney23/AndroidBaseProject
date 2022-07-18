package com.tsl.androidbase.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jowney.common.widget.banner.adapter.BannerAdapter
import com.tsl.androidbase.bean.DataBean

class ImageBannerAdapter(mDatas: List<DataBean>) : BannerAdapter<DataBean, ImageBannerAdapter.ImageHolder>(mDatas) {

    //更新数据
    fun updateData(data: List<DataBean>) {
        //这里的代码自己发挥，比如如下的写法等等
        mDatas.clear()

        mDatas.addAll(data)
        notifyDataSetChanged()
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val imageView = ImageView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.layoutParams = params
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        return ImageHolder(imageView)
    }

    override fun onBindView(holder: ImageHolder, data: DataBean, position: Int, size: Int) {
        holder.imageView.setImageResource(data.imageRes!!)
    }


    class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView

        init {
            imageView = view as ImageView
        }
    }
}
