package com.example.lastfmapp.main.albums.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lastfmapp.R
import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.databinding.ItemAlbumBinding

class AlbumListsAdapter(
    private val albums: List<AlbumRequest>
) : RecyclerView.Adapter<AlbumListsAdapter.AlbumListViewHolder>() {

    class AlbumListViewHolder(private val itemBind: ItemAlbumBinding) : RecyclerView.ViewHolder(itemBind.root) {
        fun bind(album: AlbumRequest) {
            val albumName = album.name
            val albumArtist = album.artist.name
            val albumImage = album.images[bindingAdapterPosition]
            val albumImageUrl = album.url[bindingAdapterPosition]
            itemBind.apply {
                tvTitle.text = albumName
                Glide.with(itemView)
                    .load(albumImageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.icon_error)
                    .into(imageView)
                tvDescription.text = "By ${albumArtist}"
            }
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