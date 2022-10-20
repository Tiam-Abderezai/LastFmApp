package com.example.lastfmapp.data.local

import androidx.lifecycle.LiveData
import com.example.lastfmapp.main.albums.model.AlbumEntity
import javax.inject.Inject

class RoomRepositoryImpl
@Inject constructor(private val albumDao: AlbumDao) : RoomRepository {
    override fun getAlbums(): LiveData<List<AlbumEntity>> {
        return albumDao.observeAllAlbums()
    }

    override suspend fun saveAlbum(album: AlbumEntity) {
        albumDao.insertAlbum(album)
    }

    override suspend fun deleteAlbum(album: AlbumEntity) {
        albumDao.deleteAlbum(album)
    }
}
