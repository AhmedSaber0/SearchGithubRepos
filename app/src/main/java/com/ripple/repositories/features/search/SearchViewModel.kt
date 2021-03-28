package com.ripple.repositories.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ripple.repositories.base.BaseViewModel
import com.ripple.repositories.features.search.paging.RepositoriesPagingSource
import com.ripple.repositoriesapp.domain.model.RepositoryItem
import com.ripple.repositoriesapp.domain.usecase.SearchUseCase

class SearchViewModel(private val searchUseCase: SearchUseCase) : BaseViewModel() {

    fun search(query: String): LiveData<PagingData<RepositoryItem>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { RepositoriesPagingSource(searchUseCase, query) }
        ).liveData.cachedIn(scope = viewModelScope)
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }

}
