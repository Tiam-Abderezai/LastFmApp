package com.example.lastfmapp.main.artists.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfmapp.databinding.ItemArtistBinding
import com.example.lastfmapp.main.artists.model.ArtistRequest

class ArtistListAdapter(
    private val listener: OnItemClickListener
) : PagingDataAdapter<ArtistRequest, ArtistListAdapter.ArtistListViewHolder>(ARTIST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistListViewHolder {
        val bind = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistListViewHolder(
            bind
        )
    }

    override fun onBindViewHolder(holder: ArtistListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ArtistListViewHolder(private val itemBind: ItemArtistBinding) :
        RecyclerView.ViewHolder(itemBind.root) {

        init {
            itemBind.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(artist: ArtistRequest?) {
            itemBind.tvTitle.text = artist?.name ?: ""
        }
    }

    interface OnItemClickListener {
        fun onItemClick(artist: ArtistRequest)
    }

    companion object {
        private val ARTIST_COMPARATOR = object : DiffUtil.ItemCallback<ArtistRequest>() {
            override fun areItemsTheSame(oldItem: ArtistRequest, newItem: ArtistRequest) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: ArtistRequest, newItem: ArtistRequest) =
                oldItem == newItem
        }
    }
}
