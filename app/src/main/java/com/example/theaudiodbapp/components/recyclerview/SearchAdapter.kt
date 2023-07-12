package com.example.theaudiodbapp.components.recyclerview

import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.components.ResourceLink
import com.example.theaudiodbapp.model.Album
import com.example.theaudiodbapp.model.Artist

class SearchAdapter(
    private val items: MutableList<Any>,
    private val onArtistClicked: (Artist) -> Unit,
    private val onAlbumClicked: (Album) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ARTIST = 1
        private const val VIEW_TYPE_ALBUM = 2
        private const val VIEW_TYPE_HEADER_ARTIST = 3
        private const val VIEW_TYPE_HEADER_ALBUM = 4
        private const val VIEW_TYPE_HEADER_TITLES = 5
        private const val VIEW_TYPE_HEADER_POPULAR_TITLES = 6
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER_ARTIST -> {
                DefaultViewHolder(
                    RecyclerViewHeader(
                        parent.context,
                        HeaderType.ARTISTS
                    )
                )
            }

            VIEW_TYPE_HEADER_ALBUM -> {
                DefaultViewHolder(
                    RecyclerViewHeader(
                        parent.context,
                        HeaderType.ALBUMS
                    )
                )
            }

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
            is ArtistViewHolder -> {
                holder.bind(item as Artist)
                holder.itemView.setOnClickListener { onArtistClicked(item) }
            }

            is AlbumViewHolder -> {
                holder.bind(item as Album)
                holder.itemView.setOnClickListener { onAlbumClicked(item) }
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is RecyclerViewHeader -> {
                when ((items[position] as RecyclerViewHeader).headerType) {
                    HeaderType.ARTISTS -> VIEW_TYPE_HEADER_ARTIST
                    HeaderType.ALBUMS -> VIEW_TYPE_HEADER_ALBUM
                    HeaderType.TITLES -> VIEW_TYPE_HEADER_TITLES
                    HeaderType.POPULAR_TITLES -> VIEW_TYPE_HEADER_POPULAR_TITLES
                }
            }

            is Artist -> VIEW_TYPE_ARTIST
            is Album -> VIEW_TYPE_ALBUM
            else -> throw IllegalArgumentException("Invalid view type, cannot find type for ${items[position]}")
        }
    }

    fun updateData(newItems: List<Any?>) {
        items.clear()
        items.addAll(newItems.filterNotNull())
        notifyDataSetChanged()
    }

}