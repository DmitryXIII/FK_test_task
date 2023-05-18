package ru.avacodo.fktesttask.ui.screens

import android.widget.ProgressBar
import ru.avacodo.fktesttask.databinding.FragmentLessonsListBinding
import ru.avacodo.fktesttask.domain.model.LessonDomain
import ru.avacodo.fktesttask.ui.core.BaseFragmentWithViewModel
import ru.avacodo.fktesttask.ui.core.BaseViewModel

class LessonListFragment :
    BaseFragmentWithViewModel<FragmentLessonsListBinding, List<LessonDomain>>(
        FragmentLessonsListBinding::inflate
    ) {
    override val viewModel: BaseViewModel<List<LessonDomain>>
        get() = TODO("Not yet implemented")
    override val progressBar: ProgressBar
        get() = TODO("Not yet implemented")
}