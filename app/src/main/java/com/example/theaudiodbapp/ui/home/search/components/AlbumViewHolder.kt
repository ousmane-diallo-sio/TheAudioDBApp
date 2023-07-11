package com.example.theaudiodbapp.ui.home.search.components

import androidx.recyclerview.widget.RecyclerView
import com.example.theaudiodbapp.components.LinkType
import com.example.theaudiodbapp.components.ResourceLink
import com.example.theaudiodbapp.model.Album

class AlbumViewHolder(itemView: ResourceLink) : RecyclerView.ViewHolder(itemView) {
    fun bind(album: Album) {
        val itemView = itemView as ResourceLink

        itemView.linkType = LinkType.ALBUM
        itemView.tvTitle.text = album.strAlbum
        itemView.tvSubtitle.text = album.strArtist
        // TODO : Setup image and onClickListener
    }
}