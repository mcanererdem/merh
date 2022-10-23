package com.example.merhabalar.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName(value = "name")
    val name: String?,
    @SerializedName(value = "capital")
    val capital: String?,
    @SerializedName(value = "region")
    val region: String?,
    @SerializedName(value = "currency")
    val currency: String?,
    @SerializedName(value = "language")
    val language: String?,
    @SerializedName(value = "flag")
    val imageURL: String?
) {
    val uuid: Int = 0
}
