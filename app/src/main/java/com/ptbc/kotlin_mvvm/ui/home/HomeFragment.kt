package com.ptbc.kotlin_mvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ptbc.kotlin_mvvm.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view : View = inflater.inflate(R.layout.activity_home, container, false)

        return view
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}