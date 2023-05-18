package ru.avacodo.fktesttask.ui.screens.lessons

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import ru.avacodo.fktesttask.R
import ru.avacodo.fktesttask.databinding.FragmentLessonsListBinding
import ru.avacodo.fktesttask.domain.model.FitData
import ru.avacodo.fktesttask.ui.core.BaseFragmentWithViewModel

class LessonListFragment :
    BaseFragmentWithViewModel<FragmentLessonsListBinding, List<FitData>>(
        FragmentLessonsListBinding::inflate
    ) {
    override val viewModel: LessonListViewModel by inject()

    override val progressBar by lazy { binding.lessonsProgressBar }

    private val lessonsAdapter = LessonsListAdapter() { lessonName ->
        Toast.makeText(requireContext(), lessonName, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle(getString(R.string.schedule_title))
        binding.lessonsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lessonsAdapter
        }
        if (savedInstanceState == null) {
            viewModel.getLessonsList()
        }
    }

    override fun onSuccessAction(result: List<FitData>) {
        super.onSuccessAction(result)
        lessonsAdapter.setData(result)
    }
}