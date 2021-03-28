package com.ripple.repositoriesapp.data.repo

import com.ripple.repositoriesapp.data.remote.SearchRemoteDataSource
import com.ripple.repositoriesapp.domain.model.SearchResultResponse

class SearchRepositoryImpl(private val remoteDataSource: SearchRemoteDataSource) :
    SearchRepository {

    override suspend fun search(query: String, page: Int, perPage : Int): SearchResultResponse =
        remoteDataSource.search(query = query, page = page, perPage = perPage)
}