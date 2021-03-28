package com.ripple.repositories.di

import com.ripple.repositories.app.RepositoriesApp
import org.koin.dsl.koinApplication
import org.koin.dsl.module

val appModule = module {

    koinApplication { RepositoriesApp() }
}