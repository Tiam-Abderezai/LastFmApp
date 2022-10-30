package com.example.lastfmapp.data.local

import androidx.lifecycle.asLiveData
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.example.lastfmapp.main.tracks.model.TrackEntity
import com.example.lastfmapp.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl
@Inject constructor(private val albumDao: AlbumDao, private val artistDao: ArtistDao, private val tracksDao: TracksDao) : RoomRepository {
    override fun queryAlbumEntities(): Flow<List<AlbumEntity>> {
        Log.d(Log.TAG, "albumDao.observeAllAlbums(): ${albumDao.observeAllAlbums().asLiveData().value}")
        return albumDao.observeAllAlbums()
    }

    override fun doesAlbumExist(mBid: String?): Boolean {
        Log.d(Log.TAG, "albumDao.doesAlbumExist($mBid): ${albumDao.doesAlbumExist(mBid ?: "null")})")
        return albumDao.doesAlbumExist(mBid ?: "null")
    }

    override fun queryAlbumEntity(mBid: String?): AlbumEntity {
        Log.d(Log.TAG, "albumDao.queryAlbumEntity($mBid): ${albumDao.queryAlbum(mBid ?: "null")})")
        return albumDao.queryAlbum(mBid ?: "null")
    }

    override suspend fun insertAlbum(album: AlbumEntity) {
        albumDao.insertAlbum(album)
    }

    override suspend fun deleteAlbum(album: AlbumEntity) {
        albumDao.deleteAlbum(album)
    }

    // TRACK ENTITIES and REQUESTS
    override fun queryTrackEntities(album: String): Flow<List<TrackEntity>> {
        Log.d(Log.TAG, "tracksDao.observeAllTracks(): ${tracksDao.observeAllTracks().asLiveData().value}")
        return tracksDao.observeAllTracks()
    }

    override suspend fun insertTracksEntity(tracks: List<TrackEntity>?) {
        tracksDao.insertTracksEntity(tracks)
    }

    override suspend fun deleteTracksEntity(tracks: List<TrackEntity>?) {
        tracksDao.deleteTracksEntity(tracks)
    }

    // ARTIST ENTITY
    override suspend fun saveArtist(artist: ArtistEntity) {
        artistDao.insertArtist(artist)
    }
}
