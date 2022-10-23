package com.example.lastfmapp.data.local

import com.example.lastfmapp.main.albums.model.AlbumEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    // Room
    fun getAlbums(): Flow<List<AlbumEntity>>
    suspend fun saveAlbum(album: AlbumEntity)
    suspend fun deleteAlbum(album: AlbumEntity)
}
