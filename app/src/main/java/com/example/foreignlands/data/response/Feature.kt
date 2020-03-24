package com.example.foreignlands.data.response


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
) : Parcelable