package com.cellfishpool.news.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.cellfishpool.news.R
import com.cellfishpool.news.databinding.LayoutLoadingListBinding
import com.cellfishpool.news.databinding.TopNewsItemBinding
import com.cellfishpool.news.network.model.ArticleX
import com.cellfishpool.news.ui.base.BaseViewHolder


class SearchAdapter : PagedListAdapter<ArticleX, BaseViewHolder>(SearchDiffCallback) {
    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1
    private var isLoaderVisible = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (viewType == VIEW_TYPE_LOADING) return ViewHolderLoader(
            LayoutLoadingListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        else return ViewHolder(
            TopNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position === currentList?.size!!.minus(1)) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ViewHolder(private val binding: TopNewsItemBinding) :
        BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            val doc = getItem(position)
            with(binding) {
                title.text = doc!!.title
                description.text = doc.description
                Glide.with(root.context).load(doc.urlToImage).placeholder(R.drawable.placeholder)
                    .centerCrop().into(image)
            }
        }

        override fun clear() {

        }
    }

    inner class ViewHolderLoader(private val binding: LayoutLoadingListBinding) :
        BaseViewHolder(binding.root) {
        override fun clear() {

        }

        override fun onBind(position: Int) {
            super.onBind(position)
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