package ru.avacodo.fktesttask.ui.screens.lessons

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.avacodo.fktesttask.databinding.FragmentDateListItemBinding
import ru.avacodo.fktesttask.databinding.FragmentLessonsListItemBinding
import ru.avacodo.fktesttask.domain.model.FitData
import ru.avacodo.fktesttask.domain.model.FitDataType
import ru.avacodo.fktesttask.domain.model.LessonDate
import ru.avacodo.fktesttask.domain.model.LessonDomain
import ru.avacodo.fktesttask.ui.core.BaseRvAdapter
import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT_PATTERN = "EEEE, dd MMMM"

class LessonsListAdapter(private val onClick: (name: String) -> Unit) :
    BaseRvAdapter<FitData>() {

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

    inner class LessonViewHolder(view: View) : ViewHolder(view) {
        fun bind(lesson: LessonDomain) {
            with(lesson) {
                FragmentLessonsListItemBinding.bind(itemView).apply {
                    lessonNameTextView.text = name
                    coachNameTextView.text = coachName
                    startTimeTextView.text = startTime
                    endTimeTextView.text = endTime
                    lessonPlaceTextView.text = place
                    lessonMarkerView.setBackgroundColor(Color.parseColor(markerColor))
                    lessonDurationTextView.text = duration
                }

                itemView.setOnClickListener {
                    onClick.invoke(name)
                }
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