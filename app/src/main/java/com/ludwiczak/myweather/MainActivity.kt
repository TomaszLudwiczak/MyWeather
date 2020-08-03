package com.ludwiczak.myweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ludwiczak.myweather.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            switchFragment(MainFragment())
        }
    }

    fun switchFragment(fragment: Fragment) {
        val fragmentTag = fragment.javaClass.simpleName
        val ft = supportFragmentManager.beginTransaction()
        val found = supportFragmentManager.findFragmentByTag(fragmentTag)
        if (found == null) {
            ft.addToBackStack(fragmentTag)
        }
        ft.replace(R.id.container, fragment, fragmentTag)
        ft.commit()
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            this.finish()
        }else{
            super.onBackPressed()
        }

    }
}