package ru.avacodo.fktesttask.ui.screens.lessons

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.avacodo.fktesttask.databinding.FragmentLessonsListItemBinding
import ru.avacodo.fktesttask.domain.model.LessonDomain
import ru.avacodo.fktesttask.ui.core.BaseDiffUtilCallback

class LessonsListAdapter(private val onClick: (name: String) -> Unit) :
    RecyclerView.Adapter<LessonsListAdapter.LessonsListViewHolder>() {

    private var dataList = listOf<LessonDomain>()

    fun setData(mDataList: List<LessonDomain>) {
        val diffUtilCallback = BaseDiffUtilCallback(dataList, mDataList)
        dataList = mDataList
        DiffUtil.calculateDiff(diffUtilCallback).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsListViewHolder {
        return LessonsListViewHolder(
            FragmentLessonsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: LessonsListViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class LessonsListViewHolder(view: View) : ViewHolder(view) {
        fun bind(lesson: LessonDomain) {
            FragmentLessonsListItemBinding.bind(itemView).apply {
                lessonNameTextView.text = lesson.name
                coachNameTextView.text = lesson.coachName
                startTimeTextView.text = lesson.startTime
                endTimeTextView.text = lesson.endTime
                lessonPlaceTextView.text = lesson.place
                lessonMarkerView.setBackgroundColor(Color.parseColor(lesson.markerColor))
                lessonDurationTextView.text = lesson.duration
            }
            itemView.setOnClickListener {
                onClick.invoke(lesson.name)
            }
        }
    }
}