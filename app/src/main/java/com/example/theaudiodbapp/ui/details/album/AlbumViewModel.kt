package com.example.theaudiodbapp.ui.details.album

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaudiodbapp.model.Track
import com.example.theaudiodbapp.model.TracksList
import com.example.theaudiodbapp.network.NetworkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AlbumViewModel: ViewModel() {

    val tracksFlow = MutableStateFlow<List<Track>>(emptyList())

    fun getAlbumTracks(albumId: String) {
        viewModelScope.launch {
            try {
                val tracks = NetworkManager.getTracksAsync(albumId).await()
                tracks.track?.let { tracksFlow.emit(it) }
            } catch (e: Exception) {
                Log.e("SearchViewModel", "getArtists: $e")
            }
        }
    }

}