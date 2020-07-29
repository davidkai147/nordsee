package com.ptbc.kotlin_mvvm.pagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ptbc.kotlin_mvvm.ui.home.*
import com.ptbc.kotlin_mvvm.ui.search.SearchFragment

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
        0 -> "Home"
        1 -> "Search"
        2 -> "Wish list"
        3 -> "Orders"
        4 -> "My\naccount"
        else -> ""
    }

    override fun getCount(): Int {
        return 5
    }


}