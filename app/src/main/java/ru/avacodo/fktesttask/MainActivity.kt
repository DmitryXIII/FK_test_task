package ru.avacodo.fktesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.avacodo.fktesttask.ui.core.NavigationFragmentHolder

private const val NAVIGATION_FRAGMENT_TAG = "NAVIGATOR"

class MainActivity : AppCompatActivity(), NavigationFragmentHolder {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getNavigationFragmentTag(): String {
        return NAVIGATION_FRAGMENT_TAG
    }
}