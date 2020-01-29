package com.cellfishpool.news.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
    var currentPosition: Int = 0

    abstract fun clear()
    open fun onBind(position: Int) {
        currentPosition = position
        clear()
    }

}