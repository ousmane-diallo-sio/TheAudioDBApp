package com.example.theaudiodbapp.ui.details.artist

import androidx.lifecycle.ViewModel
import com.example.theaudiodbapp.model.Artist
import com.example.theaudiodbapp.model.ArtistsList
import kotlinx.coroutines.flow.MutableStateFlow

class ArtistViewModel:  ViewModel() {

    val artistsFlow = MutableStateFlow<Artist?>(null)

}