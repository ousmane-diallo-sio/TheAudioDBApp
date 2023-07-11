package com.example.theaudiodbapp.network

import com.example.theaudiodbapp.model.ArtistsList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkArtistI {

    @GET("/api/v1/json/523532/search.php")
    fun getArtistsAsync(@Query("s") search: String): Deferred<ArtistsList>

}

interface NetworkAlbumI {

    @GET("api/v1/json/523532/searchalbum.php/")
    fun getAlbumsAsync(@Query("s") search: String): Deferred<ArtistsList>
}