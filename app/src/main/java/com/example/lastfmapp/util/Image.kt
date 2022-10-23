package com.example.lastfmapp.util

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageRequest(
    @SerializedName("#text")
    val text: String?,
    val size: String
) : Parcelable

@Entity(tableName = Constants.TABLE_IMAGE)
@Parcelize
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("#text")
    val text: String?,
    val size: String
) : Parcelable
