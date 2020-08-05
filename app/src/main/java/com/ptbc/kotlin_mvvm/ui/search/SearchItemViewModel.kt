package com.ptbc.kotlin_mvvm.ui.search

import androidx.databinding.ObservableField
import com.ptbc.kotlin_mvvm.data.model.api.CityResponse

class SearchItemViewModel(
    private val mCity: CityResponse.Cities,
    val mListener: FragmentSearchItemViewModelListener
) {

    val id: ObservableField<Int>

    val name: ObservableField<String>

    val thumbnail: ObservableField<String>

    init {
        id = ObservableField<Int>(mCity.id)!!
        name = ObservableField<String>(mCity.nameCity)!!
        thumbnail = ObservableField<String>(mCity.thumbnailCity)!!
    }

    fun onItemClick() {
        mListener.onItemClick(mCity.thumbnailCity)
    }

    interface FragmentSearchItemViewModelListener {

        fun onItemClick(thumbnail: String?)
    }
}