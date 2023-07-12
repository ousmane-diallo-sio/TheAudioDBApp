package com.example.theaudiodbapp.components.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.components.LinkType
import com.example.theaudiodbapp.components.ResourceLink
import com.example.theaudiodbapp.model.Artist

class ArtistViewHolder(itemView: ResourceLink) : RecyclerView.ViewHolder(itemView) {
    fun bind(artist: Artist) {
        val itemView = itemView as ResourceLink

        itemView.linkType = LinkType.ARTIST
        itemView.tvTitle.text = artist.strArtist
        Glide.with(itemView.context)
            .load(artist.strArtistThumb)
            .placeholder(R.drawable.placeholder_artiste)
            .into(itemView.ivOverview)
        // TODO : Add click listener
    }
}