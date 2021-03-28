package com.ripple.repositories.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.ripple.repositories.di.appModule
import com.ripple.repositories.di.viewModelModule
import com.ripple.repositoriesapp.di.dataSourceModule
import com.ripple.repositoriesapp.di.networkModule
import com.ripple.repositoriesapp.di.repositoryModule
import com.ripple.repositoriesapp.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RepositoriesApp : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidLogger()
            androidContext(this@RepositoriesApp)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    repositoryModule,
                    dataSourceModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}