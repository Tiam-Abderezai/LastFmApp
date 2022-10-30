package com.example.lastfmapp.main.tracks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfmapp.R
import com.example.lastfmapp.databinding.FragmentTrackListBinding
import com.example.lastfmapp.util.Log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackListFragment : Fragment() {

    private lateinit var _bind: FragmentTrackListBinding
    private val bind get() = _bind
    private val trackListViewModel by lazy { ViewModelProvider(this)[TrackListViewModel::class.java] }
    private val args by navArgs<TrackListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentTrackListBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        setupLifeCycleObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val albumRequest = args.albumRequest
        Log.d(Log.TAG, "")
        with(trackListViewModel) {
            isEntitySaved.observe(viewLifecycleOwner) { isSaved ->
                updateHeartIcon(isSaved)
            }
            queryTrack(albumRequest.name)
            trackQueryLiveData.observe(viewLifecycleOwner) { trackRequest ->
                Log.d(Log.TAG, "Track Request: $trackRequest")
                val adapter = TrackListAdapter(
                    tracks = trackRequest
                )
                bind.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    this.adapter = adapter
                    setHasFixedSize(true)
                    adapter.notifyDataSetChanged()
                    Log.d(Log.TAG, "Tracks Size: ${trackRequest?.size}")
                }
            }
        }
    }

    private fun setupLifeCycleObservers() {
        val albumRequest = args.albumRequest
        with(trackListViewModel) {
            trackQueryLiveData.observe(viewLifecycleOwner) { trackRequestList ->
                albumEntitiesLiveData.observe(viewLifecycleOwner) { albumEntities ->
                    isAlbumLiked(albumRequest, trackRequestList)
                    bind.heartIconImage.apply {
                        Log.d(Log.TAG, "albumEntities: $albumEntities")
                        Log.d(Log.TAG, "albumRequest.name: ${albumRequest.name}")
                        Log.d(Log.TAG, "isEntitySaved.value: ${isEntitySaved.value}")
                        setOnClickListener {
                            handleLikedAlbum(albumRequest, trackRequestList)
                        }
                    }
                }
            }
        }
    }

    private fun updateHeartIcon(isEntitySaved: Boolean?) {
        if (isEntitySaved == true) {
            Log.d(Log.TAG, "isEntitySaved True: $isEntitySaved")
            val like = context?.let { ContextCompat.getDrawable(it, R.drawable.icon_heart_enabled) }
            bind.heartIconImage.setImageDrawable(like)
        } else {
            Log.d(Log.TAG, "isEntitySaved False: $isEntitySaved")
            val dislike = context?.let { ContextCompat.getDrawable(it, R.drawable.icon_heart_disabled) }
            bind.heartIconImage.setImageDrawable(dislike)
        }
    }
}
