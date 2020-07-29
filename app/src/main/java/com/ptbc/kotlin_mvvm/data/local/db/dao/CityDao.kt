package com.ptbc.kotlin_mvvm.data.local.db.dao

import androidx.room.Query
import com.ptbc.kotlin_mvvm.data.model.db.City
import io.reactivex.Single
import kotlinx.coroutines.Deferred


interface CityDao {
    @Query("SELECT * FROM cities")
    fun getCities(): Single<List<City>>
}