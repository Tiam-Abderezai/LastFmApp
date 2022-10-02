package com.example.lastfmapp.albums

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lastfmapp.albums.model.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumDetailViewModel(val app: Application) : AndroidViewModel(app) {

    private val _albumDetailLiveData = MutableLiveData<List<Album>>()
    val albumDetailLiveData: LiveData<List<Album>> = _albumDetailLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
//            _albumDetailLiveData.postValue(listOf(Album("Album 1"), Album("Album2"), Album("Album3")))
        }
    }
}
