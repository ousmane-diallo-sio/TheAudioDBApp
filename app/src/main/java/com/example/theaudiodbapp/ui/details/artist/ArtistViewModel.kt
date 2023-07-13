package com.example.theaudiodbapp.ui.details.artist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaudiodbapp.model.Track
import com.example.theaudiodbapp.network.NetworkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArtistViewModel:  ViewModel() {

    val popularTracksFlow = MutableStateFlow<List<Track>>(emptyList())

    fun getPopularTracks(artistName: String) {
        viewModelScope.launch {
            try {
                val popularTracks = NetworkManager.getPopularTracksAsync(artistName).await()
                popularTracks.track?.let { popularTracksFlow.emit(it) }
            } catch (e: Exception) {
                Log.e("ArtistViewModel", "getPopularTracks: $e")
            }
        }
    }

}