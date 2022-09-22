package com.example.lastfmapp.api

import retrofit2.Call

interface ApiRepository {
    suspend fun getAlbumInfo(): Call<ApiResponse>
    suspend fun searchArtist(): Call<ApiResponse>
}
