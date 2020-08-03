package com.ludwiczak.myweather.ui.details.tommorow

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
class TommorowFragment : Fragment() {

    private val viewModel: TommorowViewModel by viewModels()
    private val disposable = CompositeDisposable()
    private var keyLocation: String? = null

    companion object{
        val TAG: String = TommorowFragment::class.java.simpleName
        private const val KEY: String = "KEY"

        fun newInstance(key: String?) = TommorowFragment().apply {
            arguments = bundleOf(
                KEY to key
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_tommorow_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            if(containsKey(KEY)){
                keyLocation = getString(KEY)
            }
        }

    }

}