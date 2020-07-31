package com.ptbc.kotlin_mvvm.ui.search

import com.ptbc.kotlin_mvvm.data.model.api.BlogResponse
import com.ptbc.kotlin_mvvm.data.model.api.CityResponse

interface SearchNavigator {
    fun handleError(throwable: Throwable)

    fun updateCity(cityList: List<CityResponse.Cities>?)
}