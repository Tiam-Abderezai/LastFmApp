package com.example.lastfmapp.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfmapp.databinding.FragmentArtistDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailFragment : Fragment() {

    private lateinit var _bind: FragmentArtistDetailBinding
    private val bind get() = _bind
    private val artistDetailViewModel by lazy { ViewModelProvider(this).get(ArtistDetailViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentArtistDetailBinding.inflate(inflater, container, false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        setupRecyclerView()
        setupLifeCycleObservers()
    }

    private fun setupRecyclerView() {
//        bind.recyclerView.adapter = ArtistDetailAdapter(
// //            listOf(
// //                Artist("artist one"),
// //                Artist("artist two"),
// //                Artist("artist three")
// //            )
//        )
    }

    private fun setupLifeCycleObservers() {
        with(artistDetailViewModel) {
            artistDetailLiveData.observe(viewLifecycleOwner) {
            }
        }
    }
}
