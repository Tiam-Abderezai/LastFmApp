package com.example.lastfmapp.data

import androidx.lifecycle.LiveData
import com.example.lastfmapp.data.remote.RetrofitService
import com.example.lastfmapp.util.Constants.Companion.API_KEY
import javax.inject.Inject

class RetrofitRepositoryImpl
@Inject constructor(private val retrofitService: RetrofitService) :
    RetrofitRepository {

    // Retrofit
    override suspend fun getTopAlbums() = retrofitService.getTopAlbums(API_KEY)
}
