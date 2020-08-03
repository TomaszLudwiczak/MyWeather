package com.ludwiczak.myweather.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ludwiczak.myweather.MainActivity
import com.ludwiczak.myweather.R
import com.ludwiczak.myweather.ui.details.SliderAdapter
import com.ludwiczak.myweather.ui.details.SliderViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_slider.*


@AndroidEntryPoint
class SliderFragment : Fragment() {

    private val viewModel: SliderViewModel by viewModels()
    private val disposable = CompositeDisposable()
    private var keyLocation: String? = null
    private var locationName: String? = null

    lateinit var mainActivity: MainActivity

    companion object {
        val TAG: String = SliderFragment::class.java.simpleName
        private const val KEY: String = "KEY"
        private const val LOCATION_NAME: String = "LOCATION_NAME"

        fun newInstance(key: String, locationName: String) = SliderFragment().apply {
            arguments = bundleOf(
                KEY to key,
                LOCATION_NAME to locationName
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_slider, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            if (containsKey(KEY)) { keyLocation = getString(KEY) }
            if (containsKey(LOCATION_NAME)) {
                locationName = getString(LOCATION_NAME)
                toolbar.title = locationName
            }
        }

        viewpager?.apply {
            adapter = SliderAdapter(
                mainActivity,
                keyLocation
            )
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }


        TabLayoutMediator(tabs, viewpager) { tab, position ->
            tab.text = (viewpager.adapter as SliderAdapter).titles[position]
        }.attach()

    }
}