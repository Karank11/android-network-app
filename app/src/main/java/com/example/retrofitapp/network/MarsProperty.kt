package com.example.retrofitapp.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsProperty(
    val id: String,
    @SerializedName("img_src")
    val imageSourceUrl: String,
    val type: String,
    val price: Double
): Parcelable {
    val isRental get() = type == "Rent"
}
