package com.example.theaudiodbapp.components.recyclerview

import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.components.ResourceLink
import com.example.theaudiodbapp.components.TrackItem
import com.example.theaudiodbapp.model.Album
import com.example.theaudiodbapp.model.Artist
import com.example.theaudiodbapp.model.Track

class SearchAdapter(
    private val items: MutableList<Any>,
    private val onArtistClicked: ((Artist) -> Unit)?,
    private val onAlbumClicked: ((Album) -> Unit)?,
    private val onPopularTitleClicked: ((String) -> Unit)?,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ARTIST = 1
        private const val VIEW_TYPE_ALBUM = 2
        private const val VIEW_TYPE_TRACK = 3
        private const val VIEW_TYPE_HEADER_ARTIST = 10
        private const val VIEW_TYPE_HEADER_ALBUM = 11
        private const val VIEW_TYPE_HEADER_TITLES = 12
        private const val VIEW_TYPE_HEADER_POPULAR_TITLES = 13
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
                RVHeaderViewHolder(
                    RecyclerViewHeader(
                        parent.context,
                        HeaderType.ALBUMS
                    )
                )
            }

            VIEW_TYPE_HEADER_POPULAR_TITLES -> {
                RVHeaderViewHolder(
                    RecyclerViewHeader(
                        parent.context,
                        HeaderType.POPULAR_TITLES
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

            VIEW_TYPE_TRACK -> {
                TrackViewHolder(
                    TrackItem(parent.context, null).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    }
                )
            }

            else -> throw IllegalArgumentException("Invalid view type : $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is ArtistViewHolder -> {
                holder.bind(item as Artist)
                holder.itemView.setOnClickListener { onArtistClicked?.invoke(item) }
            }

            is AlbumViewHolder -> {
                holder.bind(item as Album)
                holder.itemView.setOnClickListener { onAlbumClicked?.invoke(item) }
            }

            is TrackViewHolder -> {
                val tracks = items.filterIsInstance<Track>()
                val trackIndex = tracks.indexOf(item)
                holder.bind(trackIndex + 1, item as Track, tracks.size == trackIndex + 1)
            }

            is RVHeaderViewHolder -> {
                if (position == items.size - 1) {
                    throw IllegalArgumentException("Invalid position, a header cannot be the last item in the list")
                }
                holder.bind(items.filter { it.javaClass == items[position + 1].javaClass }.size)
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
            is Track -> VIEW_TYPE_TRACK
            else -> throw IllegalArgumentException("Invalid view type, cannot find type for ${items[position]}")
        }
    }

    fun updateData(newItems: List<Any?>) {
        items.clear()
        items.addAll(newItems.filterNotNull())
        notifyDataSetChanged()
    }

}