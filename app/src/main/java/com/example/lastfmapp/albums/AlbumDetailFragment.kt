package com.example.lastfmapp.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lastfmapp.databinding.FragmentAlbumDetailBinding

class AlbumDetailFragment : Fragment() {

    private lateinit var _bind: FragmentAlbumDetailBinding
    private val bind get() = _bind
    private val albumDetailViewModel by lazy { ViewModelProvider(this).get(AlbumDetailViewModel::class.java) }

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
//        bind.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        setupRecyclerView()
        setupLifeCycleObservers()
    }

    private fun setupRecyclerView() {
//        bind.recyclerView.adapter = AlbumListsAdapter(listOf(Album("one"), Album("two"), Album("three")))
    }

    private fun setupLifeCycleObservers() {
        with(albumDetailViewModel) {
            albumDetailLiveData.observe(viewLifecycleOwner) {
            }
        }
    }
}
