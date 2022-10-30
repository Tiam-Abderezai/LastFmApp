package com.example.lastfmapp.main.albums.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lastfmapp.data.local.RoomRepository
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.util.Event
import com.example.lastfmapp.util.Log
import com.example.lastfmapp.util.Log.TAG
import com.example.lastfmapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    roomRepo: RoomRepository
) : ViewModel() {

    private val _albumListLiveData = roomRepo.queryAlbumEntities().asLiveData()
    val albumListLiveData: LiveData<List<AlbumEntity>> = _albumListLiveData

    private val _albumStatusLiveData = MutableLiveData<Event<Resource<AlbumEntity>>>()
    val albumStatusLiveData: LiveData<Event<Resource<AlbumEntity>>> = _albumStatusLiveData
    init {
        Log.d(TAG, "")
    }
}
