package com.ripple.repositoriesapp.data.repo

import com.ripple.repositoriesapp.domain.model.SearchResultResponse

interface SearchRepository {

    suspend fun search(query: String, page: Int, perPage : Int): SearchResultResponse
}