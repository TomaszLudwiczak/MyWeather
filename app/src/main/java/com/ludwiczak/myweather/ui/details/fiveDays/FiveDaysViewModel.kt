package com.ludwiczak.myweather.ui.details.fiveDays

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ludwiczak.myweather.repo.Repository

class FiveDaysViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel()