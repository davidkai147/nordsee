package com.ptbc.kotlin_mvvm.ui.splash


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.ptbc.kotlin_mvvm.R


class SplashAdapter(mContext: Context, items: ArrayList<OnBoardItem>) :  PagerAdapter() {
    private val mContext: Context
    var onBoardItems: ArrayList<OnBoardItem> = ArrayList()

    override fun getCount(): Int {
        return onBoardItems.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun isViewFromObject(view: android.view.View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.activity_splash_item, container, false)
        val item = onBoardItems[position]

        val imageView: ImageView = itemView.findViewById(R.id.img_background) as ImageView
        imageView.setImageResource(item.image_background)

        val tv_title = itemView.findViewById(R.id.tv_header) as TextView
        tv_title.text = item.title

        val tv_content = itemView.findViewById(R.id.tv_desc) as TextView
        tv_content.text = item.description

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as RelativeLayout)
    }

    init {
        this.mContext = mContext
        onBoardItems = items

    }
}