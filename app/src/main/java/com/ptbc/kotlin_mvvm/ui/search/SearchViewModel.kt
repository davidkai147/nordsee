package com.ptbc.kotlin_mvvm.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ptbc.kotlin_mvvm.data.DataManager
import com.ptbc.kotlin_mvvm.data.model.api.CityResponse
import com.ptbc.kotlin_mvvm.ui.base.BaseViewModel
import com.ptbc.kotlin_mvvm.ui.feed.blogs.BlogNavigator
import com.ptbc.kotlin_mvvm.utils.rx.SchedulerProvider

class SearchViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<SearchNavigator>(dataManager, schedulerProvider) {

    private val cityListLiveData: MutableLiveData<List<CityResponse.Cities>> = MutableLiveData()

    init {
        fetchCities()
    }

    fun fetchCities() {
        setIsLoading(true)
        compositeDisposable.add(dataManager
            .cityApiCall
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ cityResponse ->
                if (cityResponse?.data != null) {
                    cityListLiveData.value = cityResponse.data
                }
                setIsLoading(false)
            }, { throwable ->
                setIsLoading(false)
                navigator!!.handleError(throwable)
            })
        )
    }

    fun getCityListLiveData(): LiveData<List<CityResponse.Cities>> {
        return cityListLiveData
    }
}