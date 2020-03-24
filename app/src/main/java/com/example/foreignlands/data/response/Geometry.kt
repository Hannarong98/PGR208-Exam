package com.example.foreignlands.data.response


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Geometry(
    val coordinates: List<Double>,
    val type: String
) : Parcelable