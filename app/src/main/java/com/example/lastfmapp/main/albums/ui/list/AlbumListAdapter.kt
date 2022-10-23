package com.example.lastfmapp.main.albums.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lastfmapp.R
import com.example.lastfmapp.databinding.ItemAlbumBinding
import com.example.lastfmapp.main.albums.model.AlbumEntity

class AlbumListAdapter(
    private val albums: List<AlbumEntity>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AlbumListAdapter.AlbumListViewHolder>() {

    inner class AlbumListViewHolder(private val itemBind: ItemAlbumBinding) :
        RecyclerView.ViewHolder(itemBind.root) {
        fun bind(album: AlbumEntity) {
            val albumName = album.name
            val albumArtist = album.artist.name
            val albumImage = album.image
            val albumImageUrl = album.image.text
            itemBind.apply {
                tvTitle.text = albumName
                Glide.with(itemView)
                    .load(albumImageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.icon_error)
                    .into(imageView)
                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = albums[position]
                        listener.onItemClick(item)
                    }
                }
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

    interface OnItemClickListener {
        fun onItemClick(album: AlbumEntity)
    }
}
