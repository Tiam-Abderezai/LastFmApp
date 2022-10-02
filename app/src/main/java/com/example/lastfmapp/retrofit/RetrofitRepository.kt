package com.example.lastfmapp.retrofit

import com.example.lastfmapp.albums.model.TopAlbumResponse
import retrofit2.Response

interface RetrofitRepository {
    suspend fun getTopAlbums(): Response<TopAlbumResponse>
}
