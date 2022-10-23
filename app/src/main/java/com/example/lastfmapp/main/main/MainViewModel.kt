package com.example.lastfmapp.main.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.main.artists.model.ArtistRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {
    private val _toTracksFragLiveData = MutableLiveData<AlbumRequest>()
    var toTracksFragLiveData: LiveData<AlbumRequest> = _toTracksFragLiveData

    private val _toTopAlbumFragLiveData = MutableLiveData<ArtistRequest>()
    var toTopAlbumFragLiveData: LiveData<ArtistRequest> = _toTopAlbumFragLiveData

    private val _toAlbumDetailFragLiveData = MutableLiveData<AlbumEntity>()
    var toAlbumDetailFragLiveData: LiveData<AlbumEntity> = _toAlbumDetailFragLiveData
}
