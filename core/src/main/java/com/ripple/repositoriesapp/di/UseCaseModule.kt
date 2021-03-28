package com.ripple.repositoriesapp.di

import com.ripple.repositoriesapp.domain.usecase.SearchUseCase
import com.ripple.repositoriesapp.domain.usecase.SearchUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<SearchUseCase> { SearchUseCaseImpl(searchRepository = get()) }
}