package com.example.lastfmapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lastfmapp.albums.model.AlbumEntity
import com.example.lastfmapp.data.local.RoomRepository

class FakeRoomRepositoryAndroidTest : RoomRepository {

    private val albums = mutableListOf<AlbumEntity>()

    private val observableAlbums = MutableLiveData<List<AlbumEntity>>(albums)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableAlbums.postValue(albums)
    }

    override fun getAlbums(): LiveData<List<AlbumEntity>> {
        return observableAlbums
    }

    override suspend fun saveAlbum(album: AlbumEntity) {
        albums.add(album)
    }

    override suspend fun deleteAlbum(album: AlbumEntity) {
        albums.remove(album)
    }
}
