package com.example.lastfmapp.main.top_albums.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastfmapp.data.RetrofitRepositoryImpl
import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAlbumListViewModel @Inject constructor(
//    val app: Application
    private val retrofitRepoImpl: RetrofitRepositoryImpl
) : ViewModel() {
    private val _topAlbumsLiveData = MutableLiveData<List<AlbumRequest>?>()
    val topAlbumsLiveData: LiveData<List<AlbumRequest>?> = _topAlbumsLiveData
    private val _topAlbumQueryLiveData = MutableLiveData<List<AlbumRequest>?>()
    val topAlbumQueryLiveData: LiveData<List<AlbumRequest>?> = _topAlbumQueryLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            val topAlbums = topAlbumQueryLiveData.value
            Log.d(Log.TAG, "topAlbums: $topAlbums")
            if (topAlbumQueryLiveData.value != null) {
                _topAlbumsLiveData.postValue(topAlbums)
            }
        }
    }

    fun queryTopAlbums(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitRepoImpl.getTopAlbums(query).body()?.topAlbums?.albums
            Log.d(Log.TAG, "Artist Query: $query")
            Log.d(Log.TAG, "Albums Response: $response")
            _topAlbumQueryLiveData.postValue(response)
        }
    }
}
