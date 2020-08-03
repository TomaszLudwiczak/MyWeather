package com.ludwiczak.myweather.repo

import com.ludwiczak.myweather.database.Db
import com.ludwiczak.myweather.domain.Location
import com.ludwiczak.myweather.network.WeatherApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(var db: Db, var apiService: WeatherApi) {

    fun fetchLocation(name: String): Single<List<Location>> = apiService.getCitiesByQuery(q = name)
        .subscribeOn(Schedulers.io())

    fun saveLocation(location: Location) = Single.fromCallable {
        db.locationDao().insert(location)
        location.country?.let {
            it.parentKey = location.key
            db.countryDao().insert(it)
        }
        location.administrativeArea?.let {
            it.parentKey = location.key
            db.administrativeAreaDao().insert(it)
        }
    }

    fun getFullHistory(): Single<List<Location>> = db.locationDao()
        .getAll()
        .subscribeOn(Schedulers.io())
        .map { list ->
            list.forEach { location ->
                location.administrativeArea = db.administrativeAreaDao().findByKey(location.key)
                location.country = db.countryDao().findByKey(location.key)
            }
            list
        }


}