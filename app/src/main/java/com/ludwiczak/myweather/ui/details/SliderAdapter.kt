package com.ludwiczak.myweather.ui.details

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ludwiczak.myweather.R
import com.ludwiczak.myweather.ui.details.fiveDays.FiveDaysFragment
import com.ludwiczak.myweather.ui.details.today.TodayFragment
import com.ludwiczak.myweather.ui.details.tommorow.TommorowFragment

class SliderAdapter(activity: AppCompatActivity, val key: String?) : FragmentStateAdapter(activity) {

    val titles = mutableListOf(
        activity.getString(R.string.today),
        activity.getString(R.string.tomorrow),
        activity.getString(R.string.fivedays)
    )

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> TodayFragment.newInstance(key)
            1 -> TommorowFragment.newInstance(key)
            else -> FiveDaysFragment.newInstance(key)
        }

    override fun getItemCount(): Int = 3

}