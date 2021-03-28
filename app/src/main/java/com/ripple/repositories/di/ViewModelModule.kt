package com.ripple.repositories.di

import com.ripple.repositories.features.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel(searchUseCase = get()) }
}