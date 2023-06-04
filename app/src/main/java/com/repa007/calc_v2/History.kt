package com.repa007.calc_v2

import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("calculation") val calculation: String,
    @SerializedName("result") val result: String,
    @SerializedName("time") val time: String
)
