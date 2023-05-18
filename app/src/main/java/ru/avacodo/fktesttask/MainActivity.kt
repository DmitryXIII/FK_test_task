package ru.avacodo.fktesttask

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.appbar.MaterialToolbar
import ru.avacodo.fktesttask.ui.core.NavigationFragmentHolder
import ru.avacodo.fktesttask.ui.core.TitleOwner
import ru.avacodo.fktesttask.ui.navigation.NavigationFragment

private const val NAVIGATION_FRAGMENT_TAG = "NAVIGATOR"

class MainActivity : AppCompatActivity(), NavigationFragmentHolder, TitleOwner {
    private val toolbar: MaterialToolbar by lazy { findViewById(R.id.toolbar) }
    private val toolbarTitle: TextView by lazy { toolbar.findViewById(R.id.toolbar_title_text_view) }
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.apply {
            displayOptions = DISPLAY_SHOW_CUSTOM
            customView = toolbar
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.host_container, NavigationFragment(), NAVIGATION_FRAGMENT_TAG)
                .commit()
        }
    }

    override fun setActionBarTitle(mTitle: String) {
        toolbarTitle.text = mTitle
    }

    override fun getNavigationFragmentTag(): String {
        return NAVIGATION_FRAGMENT_TAG
    }
}