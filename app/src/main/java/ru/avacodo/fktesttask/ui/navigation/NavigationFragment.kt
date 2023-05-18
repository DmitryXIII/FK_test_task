package ru.avacodo.fktesttask.ui.navigation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.avacodo.fktesttask.R
import ru.avacodo.fktesttask.databinding.FragmentNavigationBinding
import ru.avacodo.fktesttask.ui.core.BaseFragment
import ru.avacodo.fktesttask.ui.screens.lessons.LessonListFragment
import ru.avacodo.fktesttask.ui.showSnackAboveNavView

class NavigationFragment :
    BaseFragment<FragmentNavigationBinding>(FragmentNavigationBinding::inflate), Navigator {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            navigateToDestination(LessonListFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener { menuItem ->
            if (binding.bottomNavView.selectedItemId != menuItem.itemId) {
                when (menuItem.itemId) {
                    R.id.action_lessons -> {
                        navigateToDestination(LessonListFragment())
                        true
                    }

                    R.id.action_requests -> {
                        showToast(menuItem.title.toString())
                        true
                    }

                    R.id.action_add -> {
                        showToast(menuItem.title.toString())
                        true
                    }

                    R.id.action_chat -> {
                        showToast(menuItem.title.toString())
                        true
                    }

                    R.id.action_more -> {
                        showToast(menuItem.title.toString())
                        true
                    }
                    else -> false
                }
            } else {
                false
            }
        }
    }

    override fun routeToLessonDetailsScreen() {
        showToast("Lesson details")
    }

    override fun showSnackAboveNavigationView(message: String) {
        binding.bottomNavView.showSnackAboveNavView(message)
    }

    override fun navigateToDestination(destination: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.navigation_container, destination)
            .commit()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}