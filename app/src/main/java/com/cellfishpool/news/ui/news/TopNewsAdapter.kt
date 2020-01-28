package com.cellfishpool.news.ui.news

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cellfishpool.news.databinding.TopNewsItemBinding
import com.cellfishpool.news.network.model.ArticleRoom

class TopNewsAdapter : RecyclerView.Adapter<TopNewsAdapter.MyViewHolder>() {

    private var data: List<ArticleRoom> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            TopNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    fun updateDataSet(articleRoom: List<ArticleRoom>) {
        data = articleRoom
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    inner class MyViewHolder(private val binding: TopNewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val articleRoom = data[adapterPosition]
            with(binding) {
                title.text = articleRoom.title
                description.text = articleRoom.description
                Glide.with(root.context).load(articleRoom.urlToImage).placeholder(ColorDrawable(Color.BLACK)).centerCrop().into(image)
            }
        }
    }

}