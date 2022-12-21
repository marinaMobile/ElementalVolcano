package com.sunsetgames.bordersecurit.main.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Dev(
    @SerializedName("geo")
    val geo: String,
    @SerializedName("view")
    val view: String,
    @SerializedName("appsChecker")
    val appsChecker: String,
)