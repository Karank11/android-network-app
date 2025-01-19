package com.example.retrofitapp.network

import com.google.gson.annotations.SerializedName

data class MarsProperty(
    val id: String,
    @SerializedName("img_src")
    val imageSourceUrl: String,
    val type: String,
    val price: Double
)
