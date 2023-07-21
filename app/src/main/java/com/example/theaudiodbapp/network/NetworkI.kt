package com.example.theaudiodbapp.network

import com.example.theaudiodbapp.model.AlbumsList
import com.example.theaudiodbapp.model.ArtistsList
import com.example.theaudiodbapp.model.PopularTracksList
import com.example.theaudiodbapp.model.Track
import com.example.theaudiodbapp.model.TracksList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkArtistI {
    @GET("/api/v1/json/523532/search.php")
    fun getArtistsAsync(@Query("s") search: String): Deferred<ArtistsList>

}

interface NetworkAlbumI {
    @GET("api/v1/json/523532/searchalbum.php")
    fun getAlbumsAsync(@Query("s") search: String): Deferred<AlbumsList>
}

interface NetworkPopularTracksI {
    @GET("/api/v1/json/523532/track-top10.php")
    fun getPopularTracksAsync(@Query("s") artistName: String): Deferred<PopularTracksList>
}

interface NetworkTracksI {
    @GET("/api/v1/json/523532/track.php")
    fun getTracksAsync(@Query("m") albumId: String): Deferred<TracksList>
}