package com.ripple.repositories.features.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ripple.repositories.R
import com.ripple.repositories.databinding.LayoutRepositoryItemBinding
import com.ripple.repositoriesapp.domain.model.RepositoryItem

class RepositoryViewHolder(
    private val binding: LayoutRepositoryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repositoryItem: RepositoryItem?) {
        repositoryItem?.let {
            Glide.with(itemView).load(it.owner?.avatarUrl).placeholder(R.color.gray)
                .error(R.color.gray).into(binding.avatarImv)
            binding.nameTxv.text = it.name
            binding.descriptionTxv.text = it.description
        }
    }


    companion object {
        fun create(parent: ViewGroup): RepositoryViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_repository_item, parent, false)
            val binding = LayoutRepositoryItemBinding.bind(view)
            return RepositoryViewHolder(binding)
        }
    }
}