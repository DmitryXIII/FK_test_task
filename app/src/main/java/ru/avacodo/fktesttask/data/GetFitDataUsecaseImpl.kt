package ru.avacodo.fktesttask.data

import ru.avacodo.fktesttask.domain.GetFitDataUsecase
import ru.avacodo.fktesttask.domain.model.ExerciseDomain
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val DEFAULT_TAB_NAME = "undefined tab name "
private const val DEFAULT_COACH_NAME = "undefined coach name "

class GetFitDataUsecaseImpl(private val repository: FitDataRepository) : GetFitDataUsecase {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    override suspend fun getFitData(): List<ExerciseDomain> {
        val remoteData = repository.getFitData()

        return remoteData.lessons.map { lesson ->
            with(lesson) {
                ExerciseDomain(
                    name = name,
                    date = LocalDate.parse(date, dateFormatter),
                    tab = remoteData.tabs.find { it.id == tab_id }?.name ?: DEFAULT_TAB_NAME,
                    startTime = startTime,
                    endTime = endTime,
                    coachName = remoteData.trainers.find { it.id == coach_id }?.full_name
                        ?: DEFAULT_COACH_NAME,
                    place = place
                )
            }
        }
    }
}