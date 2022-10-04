package com.example.lastfmapp.data.local

import androidx.lifecycle.LiveData
import com.example.lastfmapp.albums.model.AlbumEntity

interface RoomRepository {
    // Room
    fun getAlbums(): LiveData<List<AlbumEntity>>
    suspend fun saveAlbum(album: AlbumEntity)
    suspend fun deleteAlbum(album: AlbumEntity)
}
