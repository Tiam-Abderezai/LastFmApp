package com.example.lastfmapp.util

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ImageRequest(
    @PrimaryKey
    @SerializedName("#text")
    val text: String,
    val size: String
) : Parcelable

@Entity(tableName = Constants.TABLE_IMAGE)
@Parcelize
data class ImageEntity(
    @PrimaryKey
    @SerializedName("#text")
    val text: String?,
    val size: String
) : Parcelable
