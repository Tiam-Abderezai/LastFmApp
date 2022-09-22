package com.example.lastfmapp.remote

import com.example.lastfmapp.util.Constants.Companion.API_KEY
import com.example.lastfmapp.util.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getAlbumInfo(
        artist: String?,
        album: String?
    ): Call<ApiResponse> {
        return apiService.getAlbumInfo(API_KEY)
    }

    fun searchArtist(
        artist: String?,
        album: String?
    ): Call<ApiResponse> {
        return apiService.searchArtist(API_KEY)
    }
}
