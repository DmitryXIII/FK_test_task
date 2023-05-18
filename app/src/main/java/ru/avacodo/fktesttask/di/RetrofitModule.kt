package ru.avacodo.fktesttask.di

import androidx.viewbinding.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.avacodo.fktesttask.data.FitDataApi

private const val BASE_URL = "https://olimpia.fitnesskit-admin.ru/"

val retrofitModule = module {
    single<FitDataApi> {
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addCallAdapterFactory(get())
            addConverterFactory(get())
            client(get())
        }.build().create(FitDataApi::class.java)
    }

    single<retrofit2.CallAdapter.Factory> { CoroutineCallAdapterFactory() }

    single<retrofit2.Converter.Factory> {
        GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
        }.build()
    }
}