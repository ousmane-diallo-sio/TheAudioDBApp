package com.example.theaudiodbapp.components.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.components.LinkType
import com.example.theaudiodbapp.components.ResourceLink
import com.example.theaudiodbapp.model.Album

class AlbumViewHolder(itemView: ResourceLink) : RecyclerView.ViewHolder(itemView) {
    fun bind(album: Album) {
        val itemView = itemView as ResourceLink

        itemView.linkType = LinkType.ALBUM
        itemView.tvTitle.text = album.strAlbum
        itemView.tvSubtitle.text = album.strArtist
        Glide.with(itemView.context)
            .load(album.strAlbumThumb)
            .placeholder(R.drawable.placeholder_album)
            .into(itemView.ivOverview)
    }
}