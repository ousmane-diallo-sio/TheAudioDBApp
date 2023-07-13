package com.example.theaudiodbapp.components.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.theaudiodbapp.components.TrackItem
import com.example.theaudiodbapp.model.Track

class TrackViewHolder(itemView: TrackItem) : RecyclerView.ViewHolder(itemView) {

    fun bind(index: Int, track: Track, isLastOfType: Boolean) {
        val itemView = itemView as TrackItem

        itemView.setIndex(index)
        itemView.setTitle(track.strTrack)
        if (!isLastOfType) {
            itemView.setDividerVisible(true)
        }
    }
}