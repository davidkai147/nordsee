package com.ptbc.kotlin_mvvm.ui.search

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ptbc.kotlin_mvvm.R
import com.squareup.picasso.Picasso



class SearchAdapter: RecyclerView.Adapter<SearchViewHolder>() {
    private var activitySearch: Fragment? = null
    var imagesDestination: ArrayList<Int> = ArrayList()
    var nameDestination: ArrayList<String> = ArrayList()

    var screenWidth: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        imagesDestination.add(R.drawable.australia)
        imagesDestination.add(R.drawable.cambodia)
        imagesDestination.add(R.drawable.canada)
        imagesDestination.add(R.drawable.china)
        imagesDestination.add(R.drawable.egypt)
        imagesDestination.add(R.drawable.england)
        imagesDestination.add(R.drawable.france)
        imagesDestination.add(R.drawable.india)
        imagesDestination.add(R.drawable.italy)
        imagesDestination.add(R.drawable.japan)
        imagesDestination.add(R.drawable.singapore)

        println("Array "+imagesDestination)

//        nameDestination.add("Australia")
//        nameDestination.add("Cambodia")
//        nameDestination.add("Canada")
//        nameDestination.add("China")
//        nameDestination.add("Egypt")
//        nameDestination.add("England")
//        nameDestination.add("France")
//        nameDestination.add("India")
//        nameDestination.add("Italy")
//        nameDestination.add("Japan")
//        nameDestination.add("Singapore")
        return SearchViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        var height: Int

        if (position === 1 || position === imagesDestination.size - 1) {
            height = 150
        } else {
            height = 300
        }
        val item = imagesDestination[position]
//        Picasso.get().load(item)
//                .resize(screenWidth / 2, height)
//                .centerCrop()
//                .into(holder.imageViewDestination)
        holder.imageViewDestination.setImageResource(item)

        //holder.bind()
    }

    fun StaggeredGridLayoutAdapter(fragment: Fragment
    ) {
        activitySearch = fragment
        val wm = fragment.activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
    }

    override fun getItemCount(): Int {
        return imagesDestination.size
    }
}

class SearchViewHolder(view: View): RecyclerView.ViewHolder(view){
    val imageViewDestination = view.findViewById<ImageView>(R.id.tv_destination)
    val textViewDestination = view.findViewById<TextView>(R.id.iv_destination)

    companion object{
        fun from(parent: ViewGroup): SearchViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)

            val view = layoutInflater.inflate(R.layout.fragment_search_item,parent,false)

            return SearchViewHolder(view)
        }
    }

//    fun bind() {
//       // imageViewDestination.setBackgroundResource(R.drawable.Australia)
//        val adapter = SearchAdapter()
//
//
//        for (i in 0..adapter.imagesDestination.size-1){
////            Picasso.get().load(adapter.imagesDestination[i])
////                .resize(adapter.screenWidth / 2, height)
////                .centerCrop()
////                .into(imageViewDestination)
//            imageViewDestination.setImageResource(adapter.imagesDestination[i])
//            textViewDestination.text = adapter.nameDestination[i]
//        }


    //}
}