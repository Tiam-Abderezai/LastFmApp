package com.example.lastfmapp.artists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfmapp.artists.model.ArtistRequest
import com.example.lastfmapp.databinding.ItemArtistBinding

class ArtistDetailAdapter(
    private val artists: List<ArtistRequest>
) : RecyclerView.Adapter<ArtistDetailAdapter.ArtistListsViewHolder>() {

    class ArtistListsViewHolder(private val itemBind: ItemArtistBinding) : RecyclerView.ViewHolder(itemBind.root) {
        fun bind(artist: ArtistRequest) {
            itemBind.tvTitle.text = artist.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistListsViewHolder {
        val bind = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistListsViewHolder(
            bind
        )
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    override fun onBindViewHolder(holder: ArtistListsViewHolder, position: Int) {
        val item = artists[position]
        holder.bind(item)
    }
}
