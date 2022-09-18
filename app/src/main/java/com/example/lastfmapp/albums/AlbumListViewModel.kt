package com.example.lastfmapp.albums

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumListViewModel(val app: Application) : AndroidViewModel(app) {

    private val _albumListLiveData = MutableLiveData<List<Album>>()
    val albumListLiveData: LiveData<List<Album>> = _albumListLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            _albumListLiveData.postValue(listOf(Album("Album 1"), Album("Album2"), Album("Album3")))
        }
    }
}
