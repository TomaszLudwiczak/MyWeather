package com.ludwiczak.myweather.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ludwiczak.myweather.MainActivity
import com.ludwiczak.myweather.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.regex.Pattern

@AndroidEntryPoint
class MainFragment : Fragment() {


    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainActivity: MainActivity
    private val disposable = CompositeDisposable()
    private val locationAdapter = LocationAdapter {
        viewModel.saveLocation(it)
        mainActivity.switchFragment(SliderFragment.newInstance(it.key, it.localizedName!!))
    }
    private val nameRegex: String = "^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+(?:[\\s-][a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+)*\$"
    private val pattern: Pattern = Pattern.compile(nameRegex)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.apply {
            adapter = locationAdapter
            layoutManager = LinearLayoutManager(context)
        }

        loadHistory()


        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length>2) {
                    if(pattern.matcher(newText).matches()){
                        disposable.add(
                            viewModel.getCitiesByName(newText)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    locationAdapter.list = it.toMutableList()
                                }, {
                                    it.printStackTrace()
                                })
                        )
                    }else{
                        Log.e("mainF", "Pattern doesn't match")
                    }
                } else{
                    loadHistory()
                }
                return false
            }
        })


    }


    fun loadHistory(){
        disposable.add(
            viewModel.getFullHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    locationAdapter.list = it.toMutableList()
                }, {
                    it.printStackTrace()
                })
        )
    }
}