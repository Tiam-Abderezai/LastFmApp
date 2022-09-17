package com.example.lastfmapp.artists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfmapp.databinding.ItemArtistBinding

class ArtistsAdapter(
    private val artists: List<Artist>
) : RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder>() {

    class ArtistsViewHolder(private val itemBind: ItemArtistBinding) : RecyclerView.ViewHolder(itemBind.root) {
        fun bind(artist: Artist) {
            itemBind.tvTitle.text = artist.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsViewHolder {
        val bind = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistsViewHolder(
            bind
        )
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    override fun onBindViewHolder(holder: ArtistsViewHolder, position: Int) {
        val item = artists[position]
        holder.bind(item)
    }
}
