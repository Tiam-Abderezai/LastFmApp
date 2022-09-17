package com.example.lastfmapp.artists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistsViewModel(val app: Application) : AndroidViewModel(app) {

    private val _artistsLiveData = MutableLiveData<List<Artist>>()
    val artistsLiveData: LiveData<List<Artist>> = _artistsLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            _artistsLiveData.postValue(listOf(Artist("Artist 1"), Artist("Artist 2"), Artist("Artist 3")))
        }
    }
}
