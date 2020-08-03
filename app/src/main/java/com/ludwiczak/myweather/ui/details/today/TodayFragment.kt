package com.ludwiczak.myweather.ui.details.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ludwiczak.myweather.R
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_today_details.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TodayFragment : Fragment() {

    private val viewModel: TodayViewModel by viewModels()
    private val disposable = CompositeDisposable()
    private var keyLocation: String? = null

    @Inject
    lateinit var picasso: Picasso

    companion object {
        val TAG: String = TodayFragment::class.java.simpleName
        private const val KEY: String = "KEY"

        fun newInstance(key: String?) = TodayFragment().apply {
            arguments = bundleOf(
                KEY to key
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_today_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            if (containsKey(KEY)) {
                keyLocation = getString(KEY)
            }
        }

        keyLocation?.let { keyLocation ->
            viewModel.fetchData(keyLocation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    list[0].let {
                        it.Temperature?.Metric?.let { value ->
                            with(temp_value){
                                setTextColor(
                                    when(value.Value?.toInt()){
                                        in Int.MIN_VALUE..10 -> getColor(context, android.R.color.holo_blue_light)
                                        in 10..20 -> getColor(context, android.R.color.black)
                                        else -> getColor(context, android.R.color.holo_red_light)
                                    }
                                )

                                text = String.format(
                                    Locale.getDefault(),
                                    getString(R.string.metric),
                                    value.Value,
                                    value.Unit
                                )
                            }
                        }
                        temp_desc.text = it.WeatherText
                        picasso
                            .load(
                                String.format(
                                    Locale.getDefault(),
                                    getString(R.string.icon_number),
                                    it.WeatherIcon
                                )
                            )
                            .noFade()
                            .into(temp_icon)
                        it.Pressure?.Metric?.let { value ->
                            press_value.text = String.format(
                                Locale.getDefault(),
                                getString(R.string.metric),
                                value.Value,
                                value.Unit
                            )
                        }
                        it.Wind?.Speed?.Metric?.let { value ->
                            wind_value.text = String.format(
                                Locale.getDefault(),
                                getString(R.string.metric),
                                value.Value,
                                value.Unit
                            )
                        }
                    }
                }, {
                    it.printStackTrace()
                })
        }

    }


}