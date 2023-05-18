package ru.avacodo.fktesttask.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.avacodo.fktesttask.data.dto.FitData

interface FitDataApi {
    @GET("schedule/get_v3")
    fun getFitDataAsync(
        @Query("club_id") clubID: Int = 2
    ): Deferred<FitData>
}