package com.ripple.repositoriesapp.domain.usecase

import com.ripple.repositoriesapp.data.repo.SearchRepository
import com.ripple.repositoriesapp.domain.model.SearchResultResponse

class SearchUseCaseImpl(private val searchRepository: SearchRepository) : SearchUseCase {

    override suspend operator fun invoke(
        query: String,
        page: Int,
        perPage: Int
    ): SearchResultResponse =
        searchRepository.search(query = query, page = page, perPage = perPage)
}