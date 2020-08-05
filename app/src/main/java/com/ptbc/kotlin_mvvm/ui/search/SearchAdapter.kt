package com.ptbc.kotlin_mvvm.ui.search

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ptbc.kotlin_mvvm.data.model.api.CityResponse
import com.ptbc.kotlin_mvvm.databinding.FragmentSearchItemBinding
import com.ptbc.kotlin_mvvm.ui.base.BaseViewHolder
import com.ptbc.kotlin_mvvm.utils.AppLogger
import com.squareup.picasso.Picasso


class SearchAdapter(private val mCityResponseList: MutableList<CityResponse.Cities>?) :
    RecyclerView.Adapter<BaseViewHolder>()  {
    var screenWidth: Int = 0

    private var mListener: CityAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val searchViewBinding = FragmentSearchItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return SearchViewHolder(searchViewBinding)
    }

    override fun getItemCount(): Int {
        return mCityResponseList!!.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun StaggeredGridLayoutAdapter(fragment: Fragment) {
        val wm = fragment.activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
    }

    fun setListener(listener: CityAdapterListener) {
        this.mListener = listener
    }

    fun addItems(cityList: List<CityResponse.Cities>) {
        mCityResponseList!!.addAll(cityList)
        notifyDataSetChanged()
    }

    interface CityAdapterListener {
        fun onRetryClick()
    }

    inner class SearchViewHolder(private val mBinding: FragmentSearchItemBinding) :
        BaseViewHolder(mBinding.root), SearchItemViewModel.FragmentSearchItemViewModelListener {

        private var mSearchItemViewModel: SearchItemViewModel? = null

        override fun onBind(position: Int) {
            val city = mCityResponseList!![position]

            var height: Int

            if (position == 1 || position == mCityResponseList.size - 1) {
                height = 150
            } else {
                height = 300
            }

            Picasso.get()
                .load(mCityResponseList?.get(position)?.thumbnailCity)
                .resize(screenWidth / 2, height)
                .centerCrop()
                .into(mBinding.ivDestination)

            mSearchItemViewModel = SearchItemViewModel(city, this)
            mBinding.viewModel = mSearchItemViewModel
            mBinding.executePendingBindings()
        }

        override fun onItemClick(thumbnail: String?) {
            if (thumbnail != null) {
                try {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.data = Uri.parse(thumbnail)
                    itemView.context.startActivity(intent)
                } catch (e: Exception) {
                    AppLogger.d("url error")
                }
            }
        }
    }
}


