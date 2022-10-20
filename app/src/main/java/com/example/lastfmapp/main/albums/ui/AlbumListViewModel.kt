package com.example.lastfmapp.main.albums.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.data.local.RoomRepository
import com.example.lastfmapp.util.Event
import com.example.lastfmapp.util.Log
import com.example.lastfmapp.util.Log.TAG
import com.example.lastfmapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
//    val app: Application,
    private val roomRepo: RoomRepository
) : ViewModel() {

    private val _albumListLiveData = MutableLiveData<List<AlbumEntity>>()
    val albumListLiveData: LiveData<List<AlbumEntity>> = _albumListLiveData

    private val _albumStatusLiveData = MutableLiveData<Event<Resource<AlbumEntity>>>()
    val albumStatusLiveData: LiveData<Event<Resource<AlbumEntity>>> = _albumStatusLiveData
    init {
        Log.d(TAG, "")
    }

//    fun getAlbums() = liveData(Dispatchers.IO) {
//        val topAlbums = roomRepo.getAlbums()
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = topAlbums))
//            val albums = topAlbums.value
// //            _albumStatusLiveData.postValue(Event(Resource.success(albums[0])))
//        } catch (exception: Exception) {
//            exception.message?.let { Log.d(TAG, it) }
//            emit(
//                Resource.error(
//                    data = null,
//                    message = exception.message ?: "Error Occurred!"
//                )
//            )
//        }
//    }

    fun saveAlbum(album: AlbumEntity) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = album))
            _albumStatusLiveData.postValue(Event(Resource.success(album)))
        } catch (exception: Exception) {
            exception.message?.let { Log.d(TAG, it) }
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }

    fun deleteAlbum(album: AlbumEntity) = liveData(Dispatchers.IO) {
        val deletedAlbum = roomRepo.saveAlbum(album)
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = deletedAlbum))
//            _albumListLiveData.postValue(albums!!)
        } catch (exception: Exception) {
            exception.message?.let { Log.d(TAG, it) }
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }
}
