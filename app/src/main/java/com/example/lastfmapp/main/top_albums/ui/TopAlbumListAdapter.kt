package com.example.lastfmapp.main.top_albums.ui

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lastfmapp.R
import com.example.lastfmapp.R.drawable
import com.example.lastfmapp.databinding.ItemAlbumBinding
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.example.lastfmapp.util.ImageEntity
import com.example.lastfmapp.util.Log

class TopAlbumListAdapter(
    private val topAlbums: List<AlbumRequest>?,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<TopAlbumListAdapter.TopAlbumListViewHolder>() {

    inner class TopAlbumListViewHolder(private val itemBind: ItemAlbumBinding) :
        RecyclerView.ViewHolder(itemBind.root) {

        fun bind(topAlbum: AlbumRequest) {
            val albumName = topAlbum.name
            val albummBid = topAlbum.mBid
            val albumUrl = topAlbum.url
            val artistName = topAlbum.artist.name
            val artistmBid = topAlbum.artist.mBid
            val artistUrl = topAlbum.artist.url
            val artistImages = topAlbum.artist.images

            val albumImageUrl = topAlbum.images[3].text
            itemBind.apply {
                tvTitle.text = albumName
                Glide.with(itemView)
                    .load(albumImageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(drawable.icon_error)
                    .into(imageView)
                Log.d(Log.TAG, "topAlbum.name: ${topAlbum.name}")
                Log.d(Log.TAG, "albumImage: $albumImageUrl")
                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = topAlbums?.get(position)
                        if (item != null) {
                            listener.onItemClick(item)
                        }
                    }
                }
                val red = ContextCompat.getColor(this.heartIconImage.context, R.color.red_rose)
                heartIconImage.setColorFilter(red, PorterDuff.Mode.SRC_IN)
                heartIconButton.setOnClickListener {
                    val position = bindingAdapterPosition
                    val artistEntity = ArtistEntity(mBid = artistmBid, name = artistName, url = artistUrl)
                    val imageEntity =
                        ImageEntity(text = albumImageUrl, size = artistImages?.get(position)?.size ?: "")
                    val albumEntity =
                        AlbumEntity(artist = artistEntity, image = imageEntity, mBid = albummBid, name = albumName, url = albumUrl)
                    listener.onHeartClick(albumEntity)
                    heartIconImage.setImageResource(drawable.icon_heart_enabled)
                    Toast.makeText(it.context, "CLICKED", Toast.LENGTH_SHORT).show()
                    Log.d(Log.TAG, "CLICKED")
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
        fun onHeartClick(album: AlbumEntity)
    }
}
