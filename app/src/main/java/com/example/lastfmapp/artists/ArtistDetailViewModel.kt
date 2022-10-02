package com.example.lastfmapp.artists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lastfmapp.albums.model.Artist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistDetailViewModel(val app: Application) : AndroidViewModel(app) {

    private val _artistDetailLiveData = MutableLiveData<List<Artist>>()
    val artistDetailLiveData: LiveData<List<Artist>> = _artistDetailLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
//            _artistDetailLiveData.postValue(listOf(Artist("Artist 1"), Artist("Artist 2"), Artist("Artist 3")))
        }
    }
}
