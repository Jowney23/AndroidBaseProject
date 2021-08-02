package com.tsl.androidbase.bean

import com.tsl.androidbase.R

class DataBean {
    var imageRes: Int? = null
    var imageUrl: String? = null
    var title: String? = null
    var viewType = 0

    constructor(imageRes: Int?, title: String?, viewType: Int) {
        this.imageRes = imageRes
        this.title = title
        this.viewType = viewType
    }

    constructor(imageUrl: String?, title: String?, viewType: Int) {
        this.imageUrl = imageUrl
        this.title = title
        this.viewType = viewType
    }

    companion object {
        val testData: List<DataBean>
            get() {
                val list: MutableList<DataBean> =
                    ArrayList<DataBean>()
                list.add(DataBean(R.mipmap.image1, "相信自己,你努力的样子真的很美", 1))
                list.add(DataBean(R.mipmap.image2, "极致简约,梦幻小屋", 3))
                list.add(DataBean(R.mipmap.image3, "超级卖梦人", 3))
                list.add(DataBean(R.mipmap.image4, "夏季新搭配", 1))
                list.add(DataBean(R.mipmap.image5, "绝美风格搭配", 1))
                list.add(DataBean(R.mipmap.image6, "微微一笑 很倾城", 3))
                return list
            }
    }

}