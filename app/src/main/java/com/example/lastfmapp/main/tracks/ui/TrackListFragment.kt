package com.example.lastfmapp.main.tracks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
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
        setupRecyclerView()
        setupLifeCycleObservers()
        Log.d(Log.TAG, "TRACK LIST: ${args.albumRequest}")
    }

    private fun setupRecyclerView() {
        Log.d(Log.TAG, "")
        with(trackListViewModel) {
            queryTrack(args.albumRequest.name)
            trackQueryLiveData.observe(viewLifecycleOwner) { trackRequest ->
                Log.d(Log.TAG, "Track Request: $trackRequest")
                val adapter = TrackListAdapter(
                    tracks = trackRequest
                )
//                bind.recyclerView.setHasFixedSize(true)
                bind.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    this.adapter = adapter
                    setHasFixedSize(true)
                    adapter.notifyDataSetChanged()
                    Log.d(Log.TAG, "Tracks Size: ${trackRequest?.size}")
                }
                //            adapter.submitData(viewLifecycleOwner.lifecycle, topAlbums)
            }
        }
    }

    private fun setupLifeCycleObservers() {
        with(trackListViewModel) {
//            artistListLiveData.observe(viewLifecycleOwner) {
//            }
        }
    }
}
