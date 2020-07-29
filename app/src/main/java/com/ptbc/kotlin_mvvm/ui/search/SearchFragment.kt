package com.ptbc.kotlin_mvvm.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptbc.kotlin_mvvm.R

class SearchFragment : Fragment() {
    lateinit var recyclerViewSearch: RecyclerView
    lateinit var adapter: SearchAdapter
    lateinit var imageView: ArrayList<Int>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view : View = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerViewSearch = view.findViewById(R.id.recyclerview_search)
        recyclerViewSearch.layoutManager =  GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

        adapter = SearchAdapter()
        recyclerViewSearch.adapter = adapter
        return view
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}