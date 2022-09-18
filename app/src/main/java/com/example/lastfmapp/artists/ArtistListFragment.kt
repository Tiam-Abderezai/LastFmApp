package com.example.lastfmapp.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfmapp.artists.Artist
import com.example.lastfmapp.artists.ArtistListAdapter
import com.example.lastfmapp.artists.ArtistListViewModel
import com.example.lastfmapp.databinding.FragmentArtistListBinding

class ArtistListFragment : Fragment() {

    private lateinit var _bind: FragmentArtistListBinding
    private val bind get() = _bind
    private val artistListViewModel by lazy { ViewModelProvider(this).get(ArtistListViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentArtistListBinding.inflate(inflater, container, false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        setupRecyclerView()
        setupLifeCycleObservers()
    }

    private fun setupRecyclerView() {
        bind.recyclerView.adapter = ArtistListAdapter(listOf(Artist("artist one"), Artist("artist two"), Artist("artist three")))
    }

    private fun setupLifeCycleObservers() {
        with(artistListViewModel) {
            artistListLiveData.observe(viewLifecycleOwner) {
            }
        }
    }
}
