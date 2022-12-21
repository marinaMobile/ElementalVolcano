package com.sunsetgames.bordersecurit.main.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Country(
    @SerializedName("countryCode")
    val countryCode: String
)