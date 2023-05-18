package ru.avacodo.fktesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import ru.avacodo.fktesttask.ui.core.NavigationFragmentHolder
import ru.avacodo.fktesttask.ui.navigation.NavigationFragment

private const val NAVIGATION_FRAGMENT_TAG = "NAVIGATOR"

class MainActivity : AppCompatActivity(), NavigationFragmentHolder {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.host_container, NavigationFragment(), NAVIGATION_FRAGMENT_TAG)
                .commit()
        }
    }

    override fun getNavigationFragmentTag(): String {
        return NAVIGATION_FRAGMENT_TAG
    }
}