package com.ptbc.kotlin_mvvm.ui.feed

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ptbc.kotlin_mvvm.ui.account.AccountFragment
import com.ptbc.kotlin_mvvm.ui.home.HomeFragment
import com.ptbc.kotlin_mvvm.ui.search.SearchFragment


class FeedPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private var mTabCount: Int = 0

    init {
        this.mTabCount = 0
    }

    override fun getCount(): Int {
        return mTabCount
    }

    fun setCount(count: Int) {
        mTabCount = count
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeFragment()
        1 -> SearchFragment()
        2 -> AccountFragment()
        else -> null
    }!!
}