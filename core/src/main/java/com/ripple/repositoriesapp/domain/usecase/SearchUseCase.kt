package com.ripple.repositoriesapp.domain.usecase

import com.ripple.repositoriesapp.domain.model.SearchResultResponse

interface SearchUseCase {

    suspend operator fun invoke(query: String, page: Int, perPage: Int): SearchResultResponse

}