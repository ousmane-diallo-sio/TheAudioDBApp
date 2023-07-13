package com.example.theaudiodbapp.ui.details.artist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaudiodbapp.model.Artist
import com.example.theaudiodbapp.model.ArtistsList
import com.example.theaudiodbapp.model.Track
import com.example.theaudiodbapp.network.NetworkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArtistViewModel:  ViewModel() {

    val popularTitlesFlow = MutableStateFlow<List<Track>>(emptyList())

    fun getPopularTitles(artistName: String) {
        viewModelScope.launch {
            try {
                val popularTitles = NetworkManager.getPopularTitlesAsync(artistName).await()
                popularTitles.track?.let { popularTitlesFlow.emit(it) }
            } catch (e: Exception) {
                Log.e("ArtistViewModel", "getPopularTitles: $e")
            }
        }
    }

}