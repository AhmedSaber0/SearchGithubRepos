package com.ripple.repositories.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.ripple.repositories.base.BaseFragment
import com.ripple.repositories.databinding.FragmentSearchBinding
import com.ripple.repositories.features.search.adapter.LoaderStateAdapter
import com.ripple.repositories.features.search.adapter.RepositoriesAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    private var searchJob: Job? = null
    private lateinit var loaderStateAdapter: LoaderStateAdapter
    private lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        repositoriesAdapter = RepositoriesAdapter { view, repository, i -> }
        loaderStateAdapter = LoaderStateAdapter { repositoriesAdapter.retry() }
        binding.recyclerview.adapter = repositoriesAdapter.withLoadStateHeaderAndFooter(
            header = LoaderStateAdapter { repositoriesAdapter.retry() },
            footer = LoaderStateAdapter { repositoriesAdapter.retry() })
        repositoriesAdapter.addLoadStateListener { loadState ->
            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && repositoriesAdapter.itemCount == 0
            showEmptyList(isListEmpty)

            binding.recyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading

            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                showLongToast("\uD83D\uDE28 Wooops ${it.error}")
            }
        }
    }

    private fun initViews() {
        binding.retryButton.setOnClickListener { repositoriesAdapter.retry() }

        binding.searchEditText.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else
                false
        }
    }

    private fun performSearch() {
        hideKeyboard()
        searchJob?.cancel()
        binding.recyclerview.scrollToPosition(0)
        viewModel.search(binding.searchEditText.text.toString())
            .observe(viewLifecycleOwner, {
                searchJob = lifecycleScope.launch {
                    repositoriesAdapter.submitData(it)
                }
            })
    }


    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.recyclerview.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.recyclerview.visibility = View.VISIBLE
        }
    }

    override val viewModel: SearchViewModel by viewModel()
}