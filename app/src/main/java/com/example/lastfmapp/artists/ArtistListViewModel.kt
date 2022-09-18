package com.example.lastfmapp.artists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistListViewModel(val app: Application) : AndroidViewModel(app) {

    private val _artistListLiveData = MutableLiveData<List<Artist>>()
    val artistListLiveData: LiveData<List<Artist>> = _artistListLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            _artistListLiveData.postValue(listOf(Artist("Artist 1"), Artist("Artist 2"), Artist("Artist 3")))
        }
    }
}
