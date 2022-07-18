package com.jowney.common.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jowney.common.R
import com.jowney.common.util.logger.L
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
//直接利用recyclerView加载更多有一个弊端，代码耦合比较严重
class CommonRefreshActivity : AppCompatActivity() {
    lateinit var mSrLayout: SwipeRefreshLayout
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: MRecyclerAdapter
    private var isRefreshing = false

    private var isLoadingNextPage = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_refresh)
        mSrLayout = findViewById(R.id.acr_refresh_layout)
        mRecyclerView = findViewById(R.id.acr_rv)
        mSrLayout.setOnRefreshListener {
            L.v("我开始刷新了哦！")
            Observable
                .timer(3, TimeUnit.SECONDS)
                .subscribe {
                    L.v("结束了哦！")
                    mSrLayout.isRefreshing = false;//停止下拉刷新
                }

        }
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = MRecyclerAdapter(generateData()).also { mAdapter = it }
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = recyclerView.layoutManager as? LinearLayoutManager ?: return
                val lastVisibleItem = lm.findLastVisibleItemPosition()
                val totalItemCount = lm.itemCount
                if (lastVisibleItem == totalItemCount - 1 && !isLoadingNextPage && !isRefreshing) {
                    if (totalItemCount > 0) {
                        // 在 recyclerView 滚动时向列表中添加item 并调用 notifyItemInserted() 方法更新时，
                        // 系统会给出 warning: 可能会影响RecyclerView滑动时的高度等测量
                        // 所以将这次更新 UI 的操作，延迟到下一帧绘制。
                        recyclerView.post {
                            mAdapter.addLoadItem()
                        }
                    }

                    // 在前面addLoadItem后，itemCount已经变化
                    // 增加一层判断，确保用户是滑到了正在加载的地方，才加载更多
                    val findLastVisibleItemPosition = lm.findLastVisibleItemPosition()
                    if (findLastVisibleItemPosition == lm.itemCount - 1) {
                        var dispose = Observable.timer(3, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                isLoadingNextPage = true
                                pullData()
                            }
                    }
                }
            }
        })

    }

    fun pullData() {
        mAdapter.removeLoadItem()
        mAdapter.addData(generateData())
        isLoadingNextPage = false
    }

    fun generateData(): ArrayList<RecyclerItem> {
        val items = ArrayList<RecyclerItem>()
        for (i in 0 until 20) {
            val item = RecyclerItem(TYPE_NORMAL, "第 $i 个item")
            items.add(item)
        }
        return items
    }
}


const val TYPE_FOOTER = -1001
const val TYPE_NORMAL = -1000

/**
 * Created by jamgu on 2022/05/11
 */
class MRecyclerAdapter(private val mDataList: ArrayList<RecyclerItem>) :
    RecyclerView.Adapter<MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = if (viewType == TYPE_FOOTER) {
            LayoutInflater.from(parent.context).inflate(R.layout.custom_loading_foot, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_item_recycler, parent, false)
        }

        return MViewHolder(view)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val item = mDataList[position]
        if (item.type != TYPE_FOOTER) {
            (holder.mItemView as? AppCompatTextView)?.text = item.name
        }
    }

    override fun getItemCount() = mDataList.size

    override fun getItemViewType(position: Int): Int = mDataList[position].type

    fun addLoadItem() {
        if (mDataList.size > 0 && mDataList[mDataList.size - 1].type == TYPE_FOOTER) {
            return
        }

        val footItem = RecyclerItem(TYPE_FOOTER, "正在加载")
        mDataList.add(footItem)
        notifyItemInserted(mDataList.size - 1)
    }

    fun removeLoadItem() {
        if (mDataList.isEmpty() || mDataList[mDataList.size - 1].type != TYPE_FOOTER) return

        val lastIdx = mDataList.size - 1
        mDataList.removeAt(lastIdx)
        notifyItemChanged(lastIdx)
    }

    fun addData(data: ArrayList<RecyclerItem>?) {
        if (data.isNullOrEmpty()) return

        val lastIdx = mDataList.size - 1
        mDataList.addAll(data)
        notifyItemChanged(lastIdx, data.size)
    }
}

class MViewHolder(val mItemView: View) : RecyclerView.ViewHolder(mItemView)

class RecyclerItem(val type: Int, val name: String)