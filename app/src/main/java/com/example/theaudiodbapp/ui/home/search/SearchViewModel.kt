package com.example.theaudiodbapp.ui.home.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaudiodbapp.model.ArtistsList
import com.example.theaudiodbapp.network.NetworkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    val artistsFlow = MutableStateFlow<ArtistsList>(ArtistsList(emptyList()))

    fun getArtists(search: String) {
        viewModelScope.launch {
            try {
                val artists = NetworkManager.getArtistsAsync(search).await()
                artistsFlow.emit(artists)
            } catch (e: Exception) {
                Log.e("SearchViewModel", "getArtists: $e")
            }
        }
    }

    fun resetArtists() {
        viewModelScope.launch {
            artistsFlow.emit(ArtistsList(emptyList()))
        }
    }

}