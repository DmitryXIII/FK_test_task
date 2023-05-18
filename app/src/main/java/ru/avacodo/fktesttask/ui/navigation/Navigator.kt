package ru.avacodo.fktesttask.ui.navigation

import androidx.fragment.app.Fragment

interface Navigator {
    fun routeToLessonsListScreen()

    fun routeToLessonDetailsScreen()

    fun showSnackAboveNavigationView(message: String)

    fun navigateToDestination(destination: Fragment)
}