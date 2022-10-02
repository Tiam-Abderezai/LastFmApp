package com.example.lastfmapp.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfmapp.albums.model.Album
import com.example.lastfmapp.databinding.ItemAlbumBinding

class AlbumListsAdapter(
    private val albums: List<Album>
) : RecyclerView.Adapter<AlbumListsAdapter.AlbumListViewHolder>() {

    class AlbumListViewHolder(private val itemBind: ItemAlbumBinding) : RecyclerView.ViewHolder(itemBind.root) {
        fun bind(album: Album) {
            itemBind.tvTitle.text = album.artist.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        val bind = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumListViewHolder(
            bind
        )
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {
        val item = albums[position]
        holder.bind(item)
    }
}