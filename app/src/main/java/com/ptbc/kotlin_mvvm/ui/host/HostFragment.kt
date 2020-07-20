package com.ptbc.kotlin_mvvm.ui.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ptbc.kotlin_mvvm.R
import com.ptbc.kotlin_mvvm.pagerAdapter.PagerAdapter

class HostFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.activity_host, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)

        setupViewPager()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupViewPager() {

        val adapter = PagerAdapter(childFragmentManager!!)

        viewPager.adapter = adapter

        viewPager.setOffscreenPageLimit(5)

        tabLayout.setupWithViewPager(viewPager)

    }

    companion object {
        fun newInstance() = HostFragment()
    }
}