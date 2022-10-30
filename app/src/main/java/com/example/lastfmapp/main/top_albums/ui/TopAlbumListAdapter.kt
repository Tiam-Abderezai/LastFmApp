package com.example.lastfmapp.main.top_albums.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lastfmapp.R.drawable
import com.example.lastfmapp.databinding.ItemAlbumBinding
import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.util.Log

class TopAlbumListAdapter(
    private val albumRequests: List<AlbumRequest>?,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<TopAlbumListAdapter.TopAlbumListViewHolder>() {

    private var AlbumEntity = false
    private var albumList = emptyList<AlbumRequest>()

    inner class TopAlbumListViewHolder(private val itemBind: ItemAlbumBinding) :
        RecyclerView.ViewHolder(itemBind.root) {

        fun bind(albumRequest: AlbumRequest) {
            handleBinding(albumRequest)
//            handleFavoriteAlbum(albumRequest, albumEntities)
        }

        private fun handleBinding(albumRequest: AlbumRequest) {
            val albumRequestName = albumRequest.name
            val albumRequestImageUrl = albumRequest.images[3].text
            itemBind.apply {
                tvTitle.text = albumRequestName
                Glide.with(itemView)
                    .load(albumRequestImageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(drawable.icon_error)
                    .into(imageView)
                Log.d(Log.TAG, "albumRequest.name: ${albumRequest.name}")
                Log.d(Log.TAG, "albumRequestImageUrl: $albumRequestImageUrl")

                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = albumRequests?.get(position)
                        if (item != null) {
                            listener.onItemClick(item)
                        }
                    }
                }
            }
        }

//        private fun handleFavoriteAlbum(topAlbum: AlbumRequest, albumEntities: List<AlbumEntity>?) {
//            itemBind.apply {
//                val albumName = topAlbum.name
//                val red = ContextCompat.getColor(itemBind.heartIconImage.context, R.color.red_rose)
//                heartIconImage.setColorFilter(red, PorterDuff.Mode.SRC_IN)
//                println("SHIT TIRED $albumName")
// //                if (albumEntities != null) {
// //                    var shit: AlbumEntity
// //                    for (entity in albumEntities) {
// //                        shit = entity
// //                        if (entity.name == albumName) {
// //
// //                            setImageResource(heartIconImage, drawable.icon_heart_enabled)
// //                            executePendingBindings()
// //                        } else {
// //                            setImageResource(heartIconImage, drawable.icon_heart_disabled)
// //                            executePendingBindings()
// //                        }
// //                    }
// ////                    heartIconImage.setOnClickListener { view ->
// //////                        viewModel.saveTopAlbum(entity)
// ////                    }
// //                }
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumListViewHolder {
        val bind = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopAlbumListViewHolder(
            bind
        )
    }

    override fun getItemCount(): Int {
        return albumRequests?.size ?: 0
    }

    override fun onBindViewHolder(holder: TopAlbumListViewHolder, position: Int) {
        val item = albumRequests?.get(position)
        Log.d(Log.TAG, "Album Item: ")
        if (item != null) {
            holder.bind(item)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(album: AlbumRequest)
//        fun onHeartClick(album: AlbumEntity)
    }

    @BindingAdapter("src")
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}
