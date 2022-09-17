package com.example.lastfmapp.albums

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumsViewModel(val app: Application) : AndroidViewModel(app) {

    private val _albumsLiveData = MutableLiveData<List<Album>>()
    val albumsLiveData: LiveData<List<Album>> = _albumsLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            _albumsLiveData.postValue(listOf(Album("Album 1"), Album("Album2"), Album("Album3")))
        }
    }
}
