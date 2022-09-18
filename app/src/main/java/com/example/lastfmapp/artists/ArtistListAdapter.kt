package com.example.lastfmapp.artists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfmapp.databinding.ItemArtistBinding

class ArtistListAdapter(
    private val artists: List<Artist>
) : RecyclerView.Adapter<ArtistListAdapter.ArtistListViewHolder>() {

    class ArtistListViewHolder(private val itemBind: ItemArtistBinding) : RecyclerView.ViewHolder(itemBind.root) {
        fun bind(artist: Artist) {
            itemBind.tvTitle.text = artist.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistListViewHolder {
        val bind = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistListViewHolder(
            bind
        )
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    override fun onBindViewHolder(holder: ArtistListViewHolder, position: Int) {
        val item = artists[position]
        holder.bind(item)
    }
}
