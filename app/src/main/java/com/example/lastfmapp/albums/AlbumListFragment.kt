package com.example.lastfmapp.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfmapp.databinding.FragmentAlbumListBinding

class AlbumListFragment : Fragment() {

    private lateinit var _bind: FragmentAlbumListBinding
    private val bind get() = _bind
    private val albumListViewModel by lazy { ViewModelProvider(this).get(AlbumListViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentAlbumListBinding.inflate(inflater, container, false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        setupRecyclerView()
        setupLifeCycleObservers()
    }

    private fun setupRecyclerView() {
        bind.recyclerView.adapter = AlbumListsAdapter(listOf(Album("one"), Album("two"), Album("three")))
    }

    private fun setupLifeCycleObservers() {
        with(albumListViewModel) {
            albumListLiveData.observe(viewLifecycleOwner) {
            }
        }
    }
}
