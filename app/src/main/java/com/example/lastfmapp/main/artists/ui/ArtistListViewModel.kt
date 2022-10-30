package com.example.lastfmapp.main.artists.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.lastfmapp.data.RetrofitRepositoryImpl
import com.example.lastfmapp.main.artists.model.ArtistRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtistListViewModel @Inject constructor(
//    val app: Application
    private val retrofitRepoImpl: RetrofitRepositoryImpl
) : ViewModel() {

    private val _artistListLiveData = MutableLiveData<List<ArtistRequest>>()
    val artistListLiveData: LiveData<List<ArtistRequest>> = _artistListLiveData
    private val _queryLiveData = MutableLiveData<String>()
    val queryLiveData = _queryLiveData.switchMap { queryString ->
        retrofitRepoImpl.searchArtists(queryString).cachedIn(viewModelScope)
    }

    fun queryArtist(query: String) {
        _queryLiveData.value = query
    }
}
