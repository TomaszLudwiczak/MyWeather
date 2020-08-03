package com.ludwiczak.myweather.ui.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ludwiczak.myweather.domain.Location
import com.ludwiczak.myweather.repo.Repository
import io.reactivex.schedulers.Schedulers

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getCitiesByName(name: String) = repository.apiService.getCitiesByQuery(q = name)
        .subscribeOn(Schedulers.io())

    fun getFullHistory() = repository.getFullHistory()

    fun saveLocation(location: Location) = repository.saveLocation(location)
        .subscribeOn(Schedulers.io())
        .subscribe({
            Log.e("mainVM", "saved")
        },{
            it.printStackTrace()
        })
}