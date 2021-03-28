package com.ripple.repositoriesapp.data.remote

import com.ripple.repositoriesapp.domain.model.SearchResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRemoteDataSourceImpl(private val apiService: SearchApiService) :
    SearchRemoteDataSource {

    override suspend fun search(query: String, page: Int, perPage: Int): SearchResultResponse =
        withContext(Dispatchers.IO) {
            apiService.searchRepositories(query = query, page = page, perPage = perPage)
        }
}