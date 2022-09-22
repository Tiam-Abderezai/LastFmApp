package com.example.lastfmapp.api

import com.example.lastfmapp.util.Constants.Companion.API_KEY
import javax.inject.Inject

class ApiRepositoryImpl
@Inject constructor(private val apiService: ApiService) : ApiRepository {
    override suspend fun getAlbumInfo() = apiService.getAlbumInfo(API_KEY)

    override suspend fun searchArtist() = apiService.getAlbumInfo(API_KEY)
}
