package com.example.foreignlands.data.response


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Properties(
    val icon: String,
    val id: String,
    val name: String
) : Parcelable