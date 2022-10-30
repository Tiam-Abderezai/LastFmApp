package com.example.lastfmapp.main.albums.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfmapp.databinding.ItemTrackBinding
import com.example.lastfmapp.main.tracks.model.TrackEntity
import com.example.lastfmapp.util.Log

class AlbumDetailAdapter(
    private val tracks: List<TrackEntity>?
) : RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder>() {

    inner class AlbumDetailViewHolder(private val itemBind: ItemTrackBinding) :
        RecyclerView.ViewHolder(itemBind.root) {

        fun bind(track: TrackEntity) {
            itemBind.apply {
                tvTitle.text = track.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val bind = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumDetailViewHolder(
            bind
        )
    }

    override fun getItemCount(): Int {
        return tracks?.size ?: 0
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        val item = tracks?.get(position)
        Log.d(Log.TAG, "Track Item: ")
        holder.bind(item!!)
    }
}
