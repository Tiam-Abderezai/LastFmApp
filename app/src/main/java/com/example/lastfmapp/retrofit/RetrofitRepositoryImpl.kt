package com.example.lastfmapp.retrofit

import com.example.lastfmapp.util.Constants.Companion.API_KEY
import javax.inject.Inject

class RetrofitRepositoryImpl
@Inject constructor(private val retrofitService: RetrofitService) : RetrofitRepository {
    override suspend fun getTopAlbums() = retrofitService.getTopAlbums(API_KEY)
}
