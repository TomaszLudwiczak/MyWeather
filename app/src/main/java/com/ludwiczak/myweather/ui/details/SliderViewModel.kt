package com.ludwiczak.myweather.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ludwiczak.myweather.repo.Repository

class SliderViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {


}