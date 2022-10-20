package com.example.lastfmapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.lastfmapp.data.remote.RetrofitService
import com.example.lastfmapp.main.albums.model.TopAlbumResponse
import com.example.lastfmapp.main.artists.model.ArtistRequest
import com.example.lastfmapp.main.artists.ui.page.ArtistPagingSource
import com.example.lastfmapp.main.tracks.model.SearchTrackResponse
import com.example.lastfmapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

interface RetrofitRepository {

    fun searchArtists(query: String): LiveData<PagingData<ArtistRequest>>
    suspend fun getTopAlbums(query: String): Response<TopAlbumResponse>
    suspend fun getTracks(query: String): SearchTrackResponse
}

@Singleton
class RetrofitRepositoryImpl
@Inject constructor(private val retrofitService: RetrofitService) :
    RetrofitRepository {
    override fun searchArtists(query: String): LiveData<PagingData<ArtistRequest>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArtistPagingSource(retrofitService, query) }
        ).liveData
    }

    override suspend fun getTopAlbums(query: String) = retrofitService.getTopAlbums(API_KEY, query)

    override suspend fun getTracks(query: String) = retrofitService.getTracks(API_KEY, query)
}
