package com.example.lastfmapp.main.top_albums.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lastfmapp.R
import com.example.lastfmapp.databinding.ItemAlbumBinding
import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.util.Log

class TopAlbumListAdapter(
    private val topAlbums: List<AlbumRequest>?,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<TopAlbumListAdapter.TopAlbumListViewHolder>() {

    inner class TopAlbumListViewHolder(private val itemBind: ItemAlbumBinding) :
        RecyclerView.ViewHolder(itemBind.root) {

        fun bind(topAlbum: AlbumRequest) {
            val albumName = topAlbum.name
            val albumArtist = topAlbum.artist.name
            val albumImageUrl = topAlbum.images[3].text
            itemBind.apply {
                tvTitle.text = albumName
                Glide.with(itemView)
                    .load(albumImageUrl)
                    .centerCrop()
//                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.icon_error)
                    .into(imageView)
                tvDescription.text = "By $albumArtist"
                Log.d(Log.TAG, "topAlbum.name: ${topAlbum.name}")
                Log.d(Log.TAG, "albumImage: ${albumImageUrl}")
                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = topAlbums?.get(position)
                        if (item != null) {
                            listener.onItemClick(item)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumListViewHolder {
        val bind = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopAlbumListViewHolder(
            bind
        )
    }

    override fun getItemCount(): Int {
        return topAlbums?.size ?: 0
    }

    override fun onBindViewHolder(holder: TopAlbumListViewHolder, position: Int) {
        val item = topAlbums?.get(position)
        Log.d(Log.TAG, "Album Item: ")
        holder.bind(item!!)
    }

    interface OnItemClickListener {
        fun onItemClick(album: AlbumRequest)
    }
}
