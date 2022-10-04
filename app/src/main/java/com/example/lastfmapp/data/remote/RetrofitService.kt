package com.example.lastfmapp.data.remote

import com.example.lastfmapp.albums.model.TopAlbumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("?method=artist.gettopalbums&artist=cher&format=json")
    suspend fun getTopAlbums(
        @Query("api_key") apiKey: String
    ): Response<TopAlbumResponse>
}
