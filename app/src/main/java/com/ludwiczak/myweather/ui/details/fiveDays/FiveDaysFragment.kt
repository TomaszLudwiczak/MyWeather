package com.ludwiczak.myweather.ui.details.fiveDays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ludwiczak.myweather.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class FiveDaysFragment : Fragment() {

    private val viewModel: FiveDaysViewModel by viewModels()
    private val disposable = CompositeDisposable()
    private var keyLocation: String? = null

    companion object {
        val TAG: String = FiveDaysFragment::class.java.simpleName
        private const val KEY: String = "KEY"

        fun newInstance(key: String?) = FiveDaysFragment().apply {
            arguments = bundleOf(
                KEY to key
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_fivedays_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            if (containsKey(KEY)) {
                keyLocation = getString(KEY)
            }
        }

    }

}