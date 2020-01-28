package com.cellfishpool.news.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cellfishpool.news.databinding.TopNewsItemBinding
import com.cellfishpool.news.network.model.ArticleX

class SearchAdapter : PagedListAdapter<ArticleX, SearchAdapter.ViewHolder>(SearchDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TopNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(private val binding: TopNewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(doc: ArticleX) {

            with(binding) {
                title.text = doc.title
                description.text = doc.description
                Glide.with(root.context).load(doc.urlToImage).centerCrop().into(image)
            }
        }
    }

    companion object {
        val SearchDiffCallback = object : DiffUtil.ItemCallback<ArticleX>() {
            override fun areItemsTheSame(oldItem: ArticleX, newItem: ArticleX): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: ArticleX, newItem: ArticleX): Boolean {
                return oldItem == newItem
            }
        }
    }

}