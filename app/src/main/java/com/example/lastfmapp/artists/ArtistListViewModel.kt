package com.example.lastfmapp.artists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lastfmapp.artists.model.ArtistRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistListViewModel @Inject constructor(val app: Application) : AndroidViewModel(app) {

    private val _artistListLiveData = MutableLiveData<List<ArtistRequest>>()
    val artistListLiveData: LiveData<List<ArtistRequest>> = _artistListLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
//            _artistListLiveData.postValue(
//                listOf(
//                    Artist("Artist 1"),
//                    Artist("Artist 2"),
//                    Artist("Artist 3")
//                )
//            )
        }
    }
}
