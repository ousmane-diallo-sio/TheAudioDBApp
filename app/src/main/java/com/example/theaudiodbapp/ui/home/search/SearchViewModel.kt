package com.example.theaudiodbapp.ui.home.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaudiodbapp.model.AlbumsList
import com.example.theaudiodbapp.model.ArtistsList
import com.example.theaudiodbapp.network.NetworkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    val loadingFlow = MutableStateFlow(false)

    val artistsFlow = MutableStateFlow(ArtistsList(emptyList()))
    val albumsFlow = MutableStateFlow(AlbumsList(emptyList()))

    fun getArtists(search: String) {
        viewModelScope.launch {
            loadingFlow.emit(true)
            try {
                val artists = NetworkManager.getArtistsAsync(search).await()
                artistsFlow.emit(artists)
            } catch (e: Exception) {
                Log.e("SearchViewModel", "getArtists: $e")
            }
            loadingFlow.emit(false)
        }
    }

    fun getAlbums(search: String) {
        viewModelScope.launch {
            loadingFlow.emit(true)
            try {
                val albums = NetworkManager.getAlbumsAsync(search).await()
                albumsFlow.emit(albums)
            } catch (e: Exception) {
                Log.e("SearchViewModel", "getArtists: $e")
            }
            loadingFlow.emit(false)
        }
    }

    fun resetArtists() {
        viewModelScope.launch {
            artistsFlow.emit(ArtistsList(emptyList()))
        }
    }

    fun resetAlbums() {
        viewModelScope.launch {
            albumsFlow.emit(AlbumsList(emptyList()))
        }
    }

}