package com.ripple.repositories.features.search.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.ripple.repositories.features.search.SearchViewModel.Companion.DEFAULT_PAGE_INDEX
import com.ripple.repositories.features.search.SearchViewModel.Companion.DEFAULT_PAGE_SIZE
import com.ripple.repositoriesapp.domain.model.RepositoryItem
import com.ripple.repositoriesapp.domain.usecase.SearchUseCase
import java.io.IOException

class RepositoriesPagingSource(
    private val searchUseCase: SearchUseCase,
    private val query: String
) : PagingSource<Int, RepositoryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryItem> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = searchUseCase.invoke(page = page, query = query, perPage = DEFAULT_PAGE_SIZE)
            LoadResult.Page(
                response.repositories,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.repositories.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryItem>): Int? {
        return state.anchorPosition
    }
}