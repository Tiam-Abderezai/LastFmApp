package com.example.lastfmapp.main.artists.ui.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lastfmapp.main.artists.model.ArtistRequest
import com.example.lastfmapp.data.remote.RetrofitService
import com.example.lastfmapp.util.Constants.Companion.API_KEY
import com.example.lastfmapp.util.Constants.Companion.ARTIST_STARTING_PAGE_INDEX
import com.example.lastfmapp.util.Log
import com.example.lastfmapp.util.Log.TAG
import retrofit2.HttpException
import java.io.IOException

class ArtistPagingSource(
    private val retrofitService: RetrofitService,
    private val query: String
) : PagingSource<Int, ArtistRequest>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtistRequest> {
        val index = params.key ?: ARTIST_STARTING_PAGE_INDEX
        val size = params.loadSize

        return try {
            val response = retrofitService.searchArtist(API_KEY, query, index, size)
            val artists = response.results.artistsMatched.artists

            LoadResult.Page(
                data = artists,
                prevKey = if (index == ARTIST_STARTING_PAGE_INDEX) null else index - 1,
                nextKey = if (artists.isEmpty()) null else index + 1
            )
        } catch (e: IOException) {
            Log.d(TAG, "${e.message}")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d(TAG, "${e.message}")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtistRequest>): Int? {
        TODO("Not yet implemented")
    }
}
