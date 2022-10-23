package com.example.lastfmapp.main.albums.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lastfmapp.data.local.RoomRepository
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.util.Log
import javax.inject.Inject

class AlbumDetailViewModel @Inject constructor(
    roomRepo: RoomRepository
) : ViewModel() {

    private val _albumDetailLiveData = roomRepo.getAlbums().asLiveData()
    val albumDetailLiveData: LiveData<List<AlbumEntity>> = _albumDetailLiveData

    init {
        Log.d(Log.TAG, "")
    }
}
