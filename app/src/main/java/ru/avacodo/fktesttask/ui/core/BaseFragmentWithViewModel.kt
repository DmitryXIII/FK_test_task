package ru.avacodo.fktesttask.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import ru.avacodo.fktesttask.ui.navigation.Navigator

abstract class BaseFragmentWithViewModel<VB : ViewBinding, ResultType>(
    inflateBinding: (
        inflater: LayoutInflater,
        root: ViewGroup?,
        attachToRoot: Boolean,
    ) -> VB,
) : BaseFragment<VB>(inflateBinding), ViewStateOwner<ResultType> {

    abstract val viewModel: BaseViewModel<ResultType>

    abstract val progressBar: ProgressBar

    protected lateinit var navigator: Navigator

    private fun showSnack(message: String) {
        navigator.showSnackAboveNavigationView(message)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            it.handleState(this)
        }

        if (requireActivity() is NavigationFragmentHolder) {
            navigator = parentFragmentManager
                .findFragmentByTag(
                    (requireActivity() as NavigationFragmentHolder)
                        .getNavigationFragmentTag()
                ) as Navigator
        } else {
            error("Require activity must be a type of NavigationFragmentHolder")
        }
    }

    override fun onStartLoadingAction() {
        progressBar.isVisible = true
    }

    override fun onSuccessAction(result: ResultType) {
        onEndLoading()
    }

    override fun onErrorAction(error: String) {
        onEndLoading()
        showSnack(error)
    }

    private fun onEndLoading() {
        progressBar.isVisible = false
    }
}