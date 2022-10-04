package com.example.lastfmapp.data

import androidx.lifecycle.LiveData
import com.example.lastfmapp.albums.model.TopAlbumResponse
import retrofit2.Response

interface RetrofitRepository {

    // Retrofit
    suspend fun getTopAlbums(): Response<TopAlbumResponse>
}
