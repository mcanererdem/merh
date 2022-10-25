package com.example.merhabalar.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
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
    @PrimaryKey(autoGenerate = true)
    val uuid: Int = 0
}
