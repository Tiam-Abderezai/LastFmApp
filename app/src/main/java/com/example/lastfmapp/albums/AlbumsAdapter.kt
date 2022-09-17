package com.example.lastfmapp.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfmapp.databinding.ItemAlbumBinding

class AlbumsAdapter(
    private val albums: List<Album>
) : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    class AlbumsViewHolder(private val itemBind: ItemAlbumBinding) : RecyclerView.ViewHolder(itemBind.root) {
        fun bind(album: Album) {
            itemBind.tvTitle.text = album.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val bind = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumsViewHolder(
            bind
        )
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val item = albums[position]
        holder.bind(item)
    }
}