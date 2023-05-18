package ru.avacodo.fktesttask.ui

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

fun BottomNavigationView.showSnackAboveNavView(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
        animationMode = Snackbar.ANIMATION_MODE_SLIDE
        setTextMaxLines(10)
        anchorView = this@showSnackAboveNavView
    }.show()
}