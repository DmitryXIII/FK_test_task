package ru.avacodo.fktesttask.ui.screens.lessons

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.avacodo.fktesttask.databinding.FragmentLessonsListBinding
import ru.avacodo.fktesttask.domain.model.LessonDomain
import ru.avacodo.fktesttask.ui.core.BaseFragmentWithViewModel

class LessonListFragment :
    BaseFragmentWithViewModel<FragmentLessonsListBinding, List<LessonDomain>>(
        FragmentLessonsListBinding::inflate
    ) {
    override val viewModel by viewModel<LessonListViewModel>()

    override val progressBar by lazy { binding.lessonsProgressBar }

    private val lessonsAdapter = LessonsListAdapter() { lessonName ->
        Toast.makeText(requireContext(), lessonName, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lessonsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lessonsAdapter
        }
        if (savedInstanceState == null) {
            viewModel.getLessonsList()
        }
    }

    override fun onSuccessAction(result: List<LessonDomain>) {
        super.onSuccessAction(result)
        lessonsAdapter.setData(result)
    }
}