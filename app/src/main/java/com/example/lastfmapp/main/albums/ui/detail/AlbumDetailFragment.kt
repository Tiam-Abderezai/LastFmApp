package com.example.lastfmapp.main.albums.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfmapp.R
import com.example.lastfmapp.databinding.FragmentAlbumDetailBinding
import com.example.lastfmapp.util.Log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {
    private lateinit var _bind: FragmentAlbumDetailBinding
    private val bind get() = _bind
    private val args by navArgs<AlbumDetailFragmentArgs>()
    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupLifeCycleObservers()
        setupRecyclerView()
    }

    private fun setupUi() {
        val albumName = args.albumEntity.name
        Log.d(Log.TAG, "albumName: $albumName")
        val albumArtist = args.albumEntity.artist?.name
        bind.albumDescription.text = "$albumName is an album by $albumArtist."
    }

    private fun setupRecyclerView() {
        val albumEntity = args.albumEntity
        val trackEntities = args.albumEntity.tracks
        with(albumDetailViewModel) {
            isEntitySaved.observe(viewLifecycleOwner) { isSaved ->
                Log.d(Log.TAG, "isSaved $isSaved")
                updateHeartIcon(isSaved)
            }
            Log.d(Log.TAG, "albumEntity.mBid ${albumEntity.mBid}")
            queryAlbum(albumEntity.mBid)
            Log.d(Log.TAG, "albumEntity $albumEntity")
            Log.d(Log.TAG, "trackEntities: $trackEntities")
            val adapter = AlbumDetailAdapter(
                tracks = trackEntities
            )
            bind.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
                setHasFixedSize(true)
                adapter.notifyDataSetChanged()
                Log.d(Log.TAG, "trackEntities: ${trackEntities?.size}")
            }
        }
    }

    private fun setupLifeCycleObservers() {
        val trackEntities = args.albumEntity.tracks
        with(albumDetailViewModel) {
            albumEntityLiveData.observe(viewLifecycleOwner) { albumEntity ->
                isAlbumLiked(albumEntity)
                bind.heartIconImage.apply {
                    Log.d(Log.TAG, "albumEntity: $albumEntity")
                    setOnClickListener {
                        handleLikedAlbum(albumEntity, trackEntities)
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
            val dislike =
                context?.let { ContextCompat.getDrawable(it, R.drawable.icon_heart_disabled) }
            bind.heartIconImage.setImageDrawable(dislike)
        }
    }
}
