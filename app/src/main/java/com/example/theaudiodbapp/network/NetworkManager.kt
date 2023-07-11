package com.example.theaudiodbapp.network

import android.util.Log
import com.example.theaudiodbapp.model.ArtistsList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val artistsApi = Retrofit.Builder()
        .baseUrl("https://theaudiodb.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(NetworkArtistI::class.java)

    private val albumsApi = Retrofit.Builder()
        .baseUrl("https://theaudiodb.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(NetworkAlbumI::class.java)

    fun getArtistsAsync(search: String): Deferred<ArtistsList> {
        return artistsApi.getArtistsAsync(search)
    }

}