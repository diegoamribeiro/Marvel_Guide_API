package com.diegoribeiro.marvelguide.model
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Data,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)