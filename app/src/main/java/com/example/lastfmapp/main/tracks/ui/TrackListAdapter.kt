package com.example.lastfmapp.main.tracks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfmapp.databinding.ItemTrackBinding
import com.example.lastfmapp.main.tracks.model.TrackRequest
import com.example.lastfmapp.util.Log

class TrackListAdapter(
    private val tracks: List<TrackRequest>?
) : RecyclerView.Adapter<TrackListAdapter.TrackListViewHolder>() {

    inner class TrackListViewHolder(private val itemBind: ItemTrackBinding) :
        RecyclerView.ViewHolder(itemBind.root) {

        fun bind(track: TrackRequest) {
            itemBind.apply {
                tvTitle.text = track.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackListViewHolder {
        val bind = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackListViewHolder(
            bind
        )
    }

    override fun getItemCount(): Int {
        return tracks?.size ?: 0
    }

    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) {
        val item = tracks?.get(position)
        Log.d(Log.TAG, "Track Item: ")
        holder.bind(item!!)
    }
}
