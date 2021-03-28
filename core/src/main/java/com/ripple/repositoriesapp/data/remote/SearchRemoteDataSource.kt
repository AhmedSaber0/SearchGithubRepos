package com.ripple.repositoriesapp.data.remote

import com.ripple.repositoriesapp.domain.model.SearchResultResponse

interface SearchRemoteDataSource {

    suspend fun search(query: String, page: Int, perPage : Int): SearchResultResponse

}