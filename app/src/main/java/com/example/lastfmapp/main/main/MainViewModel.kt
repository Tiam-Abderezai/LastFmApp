package com.example.lastfmapp.main.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.main.artists.model.ArtistRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {
    private val _albumsFragmentLiveData = MutableLiveData<AlbumRequest>()
    var albumsFragmentLiveData: LiveData<AlbumRequest> = _albumsFragmentLiveData

    private val _artistsFragmentLiveData = MutableLiveData<ArtistRequest>()
    var artistsFragmentLiveData: LiveData<ArtistRequest> = _artistsFragmentLiveData

}
