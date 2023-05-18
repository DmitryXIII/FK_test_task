package ru.avacodo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.avacodo.fktesttask.di.repositoryModule
import ru.avacodo.fktesttask.di.retrofitModule
import ru.avacodo.fktesttask.di.usecaseModule
import ru.avacodo.fktesttask.di.viewModelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                viewModelModule,
                usecaseModule,
                repositoryModule,
                retrofitModule
            )
        }
    }
}