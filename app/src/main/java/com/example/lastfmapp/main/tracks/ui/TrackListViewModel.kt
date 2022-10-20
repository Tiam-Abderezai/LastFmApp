package com.example.lastfmapp.main.tracks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastfmapp.data.RetrofitRepositoryImpl
import com.example.lastfmapp.main.tracks.model.TrackRequest
import com.example.lastfmapp.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackListViewModel @Inject constructor(
//    val app: Application
    private val retrofitRepoImpl: RetrofitRepositoryImpl
) : ViewModel() {

    private val _trackListLiveData = MutableLiveData<List<TrackRequest>?>()
    val trackListLiveData: LiveData<List<TrackRequest>?> = _trackListLiveData
    private val _trackQueryLiveData = MutableLiveData<List<TrackRequest>?>()
    val trackQueryLiveData: LiveData<List<TrackRequest>?> = _trackQueryLiveData

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            val tracks = trackQueryLiveData.value
            if (trackQueryLiveData.value != null) {
                _trackListLiveData.postValue(tracks)
            }
        }
    }
    fun queryTrack(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitRepoImpl.getTracks(query).results.tracksMatched.tracks
            Log.d(Log.TAG, "Album Query: $query")
            Log.d(Log.TAG, "Tracks Response: $response")
            _trackQueryLiveData.postValue(response)
        }
    }
}
