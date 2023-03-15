package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("type")val type: String,
    @SerializedName("id")val id: String,
    @SerializedName("links")val links: Links
)

data class Links (
    @SerializedName("self")val self: String
)

data class last_analysis_results(
    @SerializedName("last_analysis_results") val last_analysis_result:List<analysis>
)

data class analysis(
    @SerializedName("category") val category:String,
    @SerializedName("result") val result:String,
    @SerializedName("method") val method:String,
    @SerializedName("engine_name") val engine_name:String
    )
