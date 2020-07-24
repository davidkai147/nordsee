package com.ptbc.kotlin_mvvm.pagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ptbc.kotlin_mvvm.ui.home.*

class PagerAdapter (fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeFragment.newInstance()
        1 -> SearchFragment.newInstance()
        2 -> FavoriteFragment.newInstance()
        3 -> CartFragment.newInstance()
        4 -> ProfileFragment.newInstance()
        else -> null
    }!!

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Trang chủ"
        1 -> "Tìm kiếm"
        2 -> "Yêu thích"
        3 -> "Đơn hàng"
        4 -> "Tài khoản"
        else -> ""
    }

    override fun getCount(): Int {
        return 5
    }


}