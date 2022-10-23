package com.example.lastfmapp.data.local

import com.example.lastfmapp.main.albums.model.AlbumEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl
@Inject constructor(private val albumDao: AlbumDao) : RoomRepository {
    override fun getAlbums(): Flow<List<AlbumEntity>> {
        return albumDao.observeAllAlbums()
    }

    override suspend fun saveAlbum(album: AlbumEntity) {
        albumDao.insertAlbum(album)
    }

    override suspend fun deleteAlbum(album: AlbumEntity) {
        albumDao.deleteAlbum(album)
    }
}
