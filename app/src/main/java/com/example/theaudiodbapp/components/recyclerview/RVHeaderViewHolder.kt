package com.example.theaudiodbapp.components.recyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.theaudiodbapp.R

class RVHeaderViewHolder(itemView: RecyclerViewHeader, ) : RecyclerView.ViewHolder(itemView) {

    fun bind(itemCount: Int) {
        val itemView = itemView as RecyclerViewHeader
        itemView.addCounter(itemView.context, itemCount)
    }
}