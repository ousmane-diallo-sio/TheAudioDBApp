package com.example.theaudiodbapp.ui.home.search.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.components.ResourceLink
import com.example.theaudiodbapp.model.Album
import com.example.theaudiodbapp.model.Artist

class SearchAdapter(private val items: MutableList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ARTIST = 1
        private const val VIEW_TYPE_ALBUM = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ARTIST -> {
                ArtistViewHolder(
                    ResourceLink(parent.context, null).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    }
                )
            }

            VIEW_TYPE_ALBUM -> {
                AlbumViewHolder(
                    ResourceLink(parent.context, null).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    }
                )
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is ArtistViewHolder -> holder.bind(item as Artist)

            is AlbumViewHolder -> holder.bind(item as Album)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Artist -> VIEW_TYPE_ARTIST
            is Album -> VIEW_TYPE_ALBUM
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    fun updateData(newItems: List<Any>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

}