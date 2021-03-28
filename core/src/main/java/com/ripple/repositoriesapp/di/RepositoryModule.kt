package com.ripple.repositoriesapp.di

import com.ripple.repositoriesapp.data.repo.SearchRepository
import com.ripple.repositoriesapp.data.repo.SearchRepositoryImpl
import com.ripple.repositoriesapp.domain.usecase.SearchUseCase
import org.koin.dsl.module

val repositoryModule = module {
    factory<SearchRepository> { SearchRepositoryImpl(remoteDataSource = get()) }
}