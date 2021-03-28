package com.ripple.repositoriesapp.di

import com.ripple.repositoriesapp.data.remote.SearchRemoteDataSource
import com.ripple.repositoriesapp.data.remote.SearchRemoteDataSourceImpl
import com.ripple.repositoriesapp.data.repo.SearchRepositoryImpl
import com.ripple.repositoriesapp.domain.usecase.SearchUseCase
import org.koin.dsl.module

val dataSourceModule = module {
    factory<SearchRemoteDataSource> { SearchRemoteDataSourceImpl(apiService = get()) }
}