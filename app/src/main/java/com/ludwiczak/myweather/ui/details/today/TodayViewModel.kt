package com.ludwiczak.myweather.ui.details.today

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ludwiczak.myweather.repo.Repository

class TodayViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel(){
    fun fetchData(key: String) = repository.apiService.getCurrentConditions(key)
}