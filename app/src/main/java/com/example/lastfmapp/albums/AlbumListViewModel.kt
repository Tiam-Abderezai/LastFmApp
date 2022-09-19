package com.example.lastfmapp.albums

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lastfmapp.remote.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class AlbumListViewModel(val app: Application) : AndroidViewModel(app) {

    private val _albumListLiveData = MutableLiveData<List<Album>>()
    val albumListLiveData: LiveData<List<Album>> = _albumListLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            val albumInfo = getAlbumInfo("Cher", "Believe")
            val searchArtist = searchArtist("Cher", "Believe")
            println("shit searchArtist: ${searchArtist.awaitResponse().raw()}")
            println("shit albumInfo: ${albumInfo.awaitResponse().raw()}")
            _albumListLiveData.postValue(listOf(Album("Album 1"), Album("Album2"), Album("Album3")))
        }
    }

    private suspend fun getAlbumInfo(artist: String, album: String) = ApiClient.getAlbumInfo(artist, album)
    private suspend fun searchArtist(artist: String, album: String) = ApiClient.searchArtist(artist, album)
}
