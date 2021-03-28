package com.ripple.repositoriesapp.data.remote

import com.ripple.repositoriesapp.domain.model.SearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchResultResponse

}