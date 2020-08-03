package com.ludwiczak.myweather.ui.details.tommorow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ludwiczak.myweather.repo.Repository

class TommorowViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel()