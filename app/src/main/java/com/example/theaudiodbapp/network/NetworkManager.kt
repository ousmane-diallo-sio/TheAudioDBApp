package com.example.theaudiodbapp.network

import com.example.theaudiodbapp.model.AlbumsList
import com.example.theaudiodbapp.model.ArtistsList
import com.example.theaudiodbapp.model.PopularTracksList
import com.example.theaudiodbapp.model.Track
import com.example.theaudiodbapp.model.TracksList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val api = Retrofit.Builder()
        .baseUrl("https://theaudiodb.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private val artistsApi = api.create(NetworkArtistI::class.java)

    private val albumsApi = api.create(NetworkAlbumI::class.java)

    private val popularTracksApi = api.create(NetworkPopularTracksI::class.java)

    private val tracksApi = api.create(NetworkTracksI::class.java)

    fun getArtistsAsync(search: String): Deferred<ArtistsList> {
        return artistsApi.getArtistsAsync(search)
    }

    fun getAlbumsAsync(search: String): Deferred<AlbumsList> {
        return albumsApi.getAlbumsAsync(search)
    }

    fun getPopularTracksAsync(artistName: String): Deferred<PopularTracksList> {
        return popularTracksApi.getPopularTracksAsync(artistName)
    }

    fun getTracksAsync(albumId: String): Deferred<TracksList> {
        return tracksApi.getTracksAsync(albumId)
    }

}