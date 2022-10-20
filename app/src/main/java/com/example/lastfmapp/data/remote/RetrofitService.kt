package com.example.lastfmapp.data.remote

import com.example.lastfmapp.main.albums.model.TopAlbumResponse
import com.example.lastfmapp.main.artists.model.SearchArtistResponse
import com.example.lastfmapp.main.tracks.model.SearchTrackResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("?method=artist.search&format=json")
    suspend fun searchArtist(
        @Query("api_key") apiKey: String,
        @Query("artist") query: String,
        @Query("page") page: Int,
        @Query("itemsPerPage") perPage: Int
    ): SearchArtistResponse

    @GET("?method=artist.gettopalbums&format=json")
    suspend fun getTopAlbums(
        @Query("api_key") apiKey: String,
        @Query("artist") query: String
    ): Response<TopAlbumResponse>

    @GET("?method=track.search&format=json")
    suspend fun getTracks(
        @Query("api_key") apiKey: String,
        @Query("track") query: String
    ): SearchTrackResponse
}
