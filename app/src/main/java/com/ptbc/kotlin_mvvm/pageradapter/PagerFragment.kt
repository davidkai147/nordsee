package com.ptbc.kotlin_mvvm.pageradapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ptbc.kotlin_mvvm.R

class PagerFragment: Fragment() {
    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view : View = inflater.inflate(R.layout.activity_base, container, false)

        setupViewPager()
        return view
    }

    private fun setupViewPager() {

        val adapter = PagerAdapter(childFragmentManager!!)

        viewPager.adapter = adapter

        viewPager.setOffscreenPageLimit(4)

        tabLayout.setupWithViewPager(viewPager)

    }

    companion object {
        fun newInstance() = PagerFragment()
    }
}