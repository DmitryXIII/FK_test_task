package ru.avacodo.fktesttask.ui.screens.lessons

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.avacodo.fktesttask.databinding.FragmentDateListItemBinding
import ru.avacodo.fktesttask.databinding.FragmentLessonsListItemBinding
import ru.avacodo.fktesttask.domain.model.FitData
import ru.avacodo.fktesttask.domain.model.FitDataType
import ru.avacodo.fktesttask.domain.model.LessonDate
import ru.avacodo.fktesttask.domain.model.LessonDomain
import ru.avacodo.fktesttask.ui.core.BaseDiffUtilCallback
import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT_PATTERN = "EEEE, dd MMMM"

class LessonsListAdapter(private val onClick: (name: String) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    private var dataList = listOf<FitData>()

    fun setData(mDataList: List<FitData>) {
        val diffUtilCallback = BaseDiffUtilCallback(dataList, mDataList)
        dataList = mDataList
        DiffUtil.calculateDiff(diffUtilCallback).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            FitDataType.DATE.value -> {
                DateViewHolder(
                    FragmentDateListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).root
                )
            }
            else -> {
                LessonViewHolder(
                    FragmentLessonsListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).root
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val dataItem = dataList[position]) {
            is LessonDate -> {
                (holder as DateViewHolder).bind(dataItem)
            }
            is LessonDomain -> {
                (holder as LessonViewHolder).bind(dataItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position].getType()) {
            FitDataType.DATE -> {
                FitDataType.DATE.value
            }
            FitDataType.LESSON -> {
                FitDataType.LESSON.value
            }
        }
    }

    override fun getItemCount() = dataList.size

    inner class LessonViewHolder(view: View) : ViewHolder(view) {
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

    inner class DateViewHolder(view: View) : ViewHolder(view) {
        private val sdf = SimpleDateFormat(FORMAT_PATTERN, Locale.getDefault())
        fun bind(date: LessonDate) {
            FragmentDateListItemBinding.bind(itemView).apply {
                dateHeaderTextView.text = sdf.format(date.date)
            }
        }
    }
}