package com.example.lastfmapp.main.albums.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastfmapp.data.local.RoomRepositoryImpl
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.example.lastfmapp.main.tracks.model.TrackEntity
import com.example.lastfmapp.util.ImageEntity
import com.example.lastfmapp.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val roomRepoImpl: RoomRepositoryImpl
) : ViewModel() {

    private val _isEntitySaved = MutableLiveData<Boolean>()
    val isEntitySaved: LiveData<Boolean> = _isEntitySaved
    var isSaved: Boolean = false

    private val _albumEntityLiveData = MutableLiveData<AlbumEntity>()
    val albumEntityLiveData: LiveData<AlbumEntity> = _albumEntityLiveData

    fun queryAlbum(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = roomRepoImpl.queryAlbumEntity(query)
            Log.d(Log.TAG, "Album Query: $query")
            Log.d(Log.TAG, "Album Response: $response")
            _albumEntityLiveData.postValue(response)
        }
    }

    private fun saveTopAlbum(album: AlbumEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepoImpl.insertAlbum(album)
            Log.d(Log.TAG, "Album Entity: $album")
        }
    }

    private fun deleteTopAlbum(album: AlbumEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepoImpl.deleteAlbum(album)
            Log.d(Log.TAG, "Album Entity: $album")
        }
    }

    fun handleLikedAlbum(albumEntity: AlbumEntity, tracks: List<TrackEntity>?) {
        viewModelScope.launch(Dispatchers.IO) {
            val entityAlbum = getAlbumEntity(albumEntity, tracks)
            Log.d(Log.TAG, "tracks: $tracks")
            Log.d(Log.TAG, "entityTracks: $tracks")
            Log.d(Log.TAG, "albumEntity.mBid: ${albumEntity.mBid}")
            isSaved = roomRepoImpl.doesAlbumExist(albumEntity.mBid)
            Log.d(Log.TAG, "isSaved: $isSaved")
            if (isSaved) {
                deleteTopAlbum(entityAlbum)
                _isEntitySaved.postValue(false)
                Log.d(Log.TAG, "isSaved True: $isSaved")
            } else {
                saveTopAlbum(entityAlbum)
                _isEntitySaved.postValue(true)
                Log.d(Log.TAG, "isSaved False: $isSaved")
            }
        }
    }

    fun isAlbumLiked(albumEntity: AlbumEntity?) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(Log.TAG, "albumEntity.mBid: ${albumEntity?.mBid}")
            isSaved = roomRepoImpl.doesAlbumExist(albumEntity?.mBid)
            Log.d(Log.TAG, "isSaved: $isSaved")
            _isEntitySaved.postValue(isSaved)
        }
    }

    private fun getAlbumEntity(
        albumEntity: AlbumEntity,
        trackEntities: List<TrackEntity>?
    ): AlbumEntity {
        val albumName = albumEntity.name
        val albummBid = albumEntity.mBid
        val albumUrl = albumEntity.url
        val artistName = albumEntity.artist?.name
        val artistmBid = albumEntity.artist?.mBid
        val artistUrl = albumEntity.artist?.url
        val albumImageSize = albumEntity.image.size
        val albumImageUrl = albumEntity.image.text
        val artistEntity =
            ArtistEntity(mBid = artistmBid, name = artistName ?: "Unknown", url = artistUrl)
        val imageEntity =
            ImageEntity(
                text = albumImageUrl,
                size = albumImageSize.get(0).toString()
            )
        return AlbumEntity(
            mBid = albummBid ?: "",
            artist = artistEntity,
            image = imageEntity,
            name = albumName,
            url = albumUrl,
            tracks = trackEntities
        )
    }
}
